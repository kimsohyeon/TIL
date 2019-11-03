# ClickHouse



## MergeTree
ClickHouse의 Engine은 데이터의 물리구조와 질의성능을 정함 \
일빈적으로 MergeTree를 사용


MergeTree Parameters
- date column
- (option) sampling expression
- primary key tuple
- index (Primary Key) granularity

[Example]
```
ENGINE = MergeTree(Date, (UserID, Query, MappingKey, Timstamp), 8192)
```

MergeTree Features
- sorting by primary key
- partitioning of rows
- replication
- sampling data

## ClickHouse 실습
### 1. Creating Databases And Tables
- database - table 구조
- 일반적인 RDB의 create문과 유사

[format]
```
CREATE DATABASE database_name;

CREATE TABLE [IF NOT EXISTS] [db.]table_name [ON CLUSTER cluster]
(
    name1 [type1] [DEFAULT|MATERIALIZED|ALIAS expr1] [TTL expr1],
    name2 [type2] [DEFAULT|MATERIALIZED|ALIAS expr2] [TTL expr2],
    ...
    INDEX index_name1 expr1 TYPE type1(...) GRANULARITY value1,
    INDEX index_name2 expr2 TYPE type2(...) GRANULARITY value2
) ENGINE = MergeTree()
[PARTITION BY expr]
[ORDER BY expr]
[PRIMARY KEY expr]
[SAMPLE BY expr]
[TTL expr]
[SETTINGS name=value, ...];
```


[example]
```
CARETE DATABASE test;

USE test;

CREATE TABLE click
(
    ID String,
    Date Date,
    Timestamp DateTime,
    MappingKey String,
    UserID String,
    Tab Nullable(String),
    DocID Nullable(String),
    Query String,
    Day Nullable(UInt64),
    Hour Nullable(UInt64),
    Order Nullable(UInt64)
) ENGINE = MergeTree(Date, (UserID, Query, MappingKey, Timstamp), 8192);
```

> 같은 row를 가지더라고 parquet은 하나의 파일로 안들어가고 json은 하나의 파일로 들어감\
parquet은 repartition해서 여러개의 파일로 만든뒤에 for loop로 넣음\
Parquet : 1.2G , JSONEachRow : 2.9G

> sorting by primary key = 어떤 상황에서 중요한 feature인가?\
-> primary key를 유저id로 정하면 UV를 구하기 쉽다.?\
자동으로 스키마를 만들어 주는가?



### 2. Inserting Data

- merging insert data after 15 minutes
- perform non-scheduled merge using the optimize query

[Non-scheduled Merge]
```
OPTIMIZE TABLE click PARTITION 201910;
```

[Insert Parquet]
```
cat {filename} | clickhouse-client --query="INSERT INTO {some_table} FORMAT Parquet"
```

### 3. Inserting, Updating, And Deleing Data And Columns

- column-oriented database이기 때문에 컬럼을 스키마에 추가, 삭제하는 것이 쉬움
- bulk update, delete를 지원
- 로우 단위로 update, delete할 수 없음
- 필터링 조건을 주고 업데이트/삭제 하는 것이 가능함

[Alter Table]
```
ALTER TABLE table_name ADD COLUMN column_name column_type;
ALTER TABLE visits DROP COLUMN location;
```

[Update table]
```
ALTER TABLE visits UPDATE url = 'http://example2.com' WHERE duration < 15;
```

> 필드추가, 데이터 업데이트를 손쉽게 할 수 있음\
서비스 초기에 데이터구조 변화가 빈번할 때 장점\
예를 들어 서비스 디바이스가 추가될때, 기존의 value를 바꿔야될 수도 있음\
service -> serviceapp, servicenavi






## 참고자료
- CentOS에 ClickHouse 설치하기 : https://www.digitalocean.com/community/tutorials/how-to-install-and-use-clickhouse-on-centos-7
- ClickHouse tutorial : https://clickhouse.yandex/tutorial.html
- ClickHouse Input/Output Data Formats : https://clickhouse.yandex/docs/en/interfaces/formats/
