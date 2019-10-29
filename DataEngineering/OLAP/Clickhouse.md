https://www.digitalocean.com/community/tutorials/how-to-install-and-use-clickhouse-on-centos-7

1. creating databases and tables
- database - table 구조
- 일반적인 RDB의 create문과 유사
- engine은 데이터의 물리구조, 질의성능을 정함
  -mergetree를 일반적으로 사용 
  - => sorting by primary key, partitioning of rows, replicating, sampling data


```
CREATE DATABASE test;

CREATE TABLE table_name
(
    column_name1 column_type [options],
    column_name2 column_type [options],
    ...
) ENGINE = engine


CREATE TABLE visits (
  id UInt64,
  duration Float64,
  url String,
  created DateTimeq
) ENGINE = MergeTree()
PRIMARY KEY id
ORDER BY id;
```



>sorting by primary key = 어떤 상황에서 중요한 feature인가?

>자동으로 스키마를 만들어 주는가?



2. inserting, updating, and deleing data and columns

- 컬럼을 스키마에 추가, 삭제하는 것이 쉬움

```
ALTER TABLE table_name ADD COLUMN column_name column_type;
ALTER TABLE visits DROP COLUMN location;
```

- bulk update, delete를 지원
- 로우 단위로 update, delete할 수 없음
- 필터링 조건을 주고 업데이트/삭제 하는 것이 가능함

```
ALTER TABLE visits UPDATE url = 'http://example2.com' WHERE duration < 15;
```

> 필드추가, 데이터 업데이트를 손쉽게 할 수 있음
서비스 초기에 데이터구조 변화가 빈번할 때 장점
예를 들어 서비스 디바이스가 추가될때, 기존의 value를 바꿔야될 수도 있음
service -> serviceapp, servicenavi



3. insert data formats
https://clickhouse.yandex/docs/en/interfaces/formats/
