# 배경

기존에 사용하고 있는 ElasticSearch의 장단점을 살펴보고 대안이 될 수 있는 OLAP 후보군에 대해 알아보자

- 하둡기반 SQL 시스템(Hive, Impala, Presto, Spark)보다 질의 속도가 빠르다.
  - 인덱싱에 최적화된 구조를 사용
  - 상대적으로 노드 사이에 데이터 이동이 없음
  - -> 질의 속도는 빠르지만 커다란 두 테이블 조인이 안됨   

OLAP 후보군
- ElasticSearch
- ClickHouse
- Druid
- Kudu



# OLAP란?
- OLAP(Online Analytical Processing)
- 키워드 : #대규모데이터 #다차원 #큐브 #실시간처리 #대화식분석  
- 대규모 데이터를 큐브형태로 다루며 다차원 정보를 실시간으로 분석할 수 있다.
- __FASMI__ __F__ast __A__nalysis of __S__hared __M__ultidimensional __I__nformation
  실시간 / 편리한 분석 / 멀티 엑세스 / 다차원
- 큰 데모 데이터(성연령)와 실시간성 데이터(작은 사이즈)의 데이터를 조인할때는 OLTP(Hbase, redis?)가 유용하다.



# 질의 속도에 영향을 주는 기법들

| Doc ID |	Country |	Browser |	Locale |	Impressions |
| :----- | :----- | :------ | :------ | :------ |
| 0 |	CA |	Chrome |	en |	400
| 1 |	CA |	Firefox |	fr |	200
| 2 |	MX |	Safari |	es |	300
| 3 |	MX |	Safari |	en | 	100
| 4 |	USA |	Chrome |	en |	600
| 5 |	USA |	Firefox |	es |	200
| 6 |	USA |	Firefox |	en |	400


- Sorted index
| Country |	Doc | ID
|:-------- | :----- | :----|
| CA |	0-1
| MX |	2-3
| USA |	4-6

```
SELECT SUM(Impressions) FROM Table WHERE Country = ‘USA’
```
USA를 찾는데 걸리는 시간 O(n) -> O(1)
Primary key에 대해서만 적용할 수 있고, aggregation cost는 f(3)이다.

- Inverted index
| Browser |	Doc ID |	Locale |	Doc ID |
|:------- | :------ | :------ | :------ |
| Chrome |	0,4 |	en 	| 0,3,4,6
| Firefox |	1,5,6 |	es 	| 2,5
| Safari |	2,3 |	fr 	| 1

```
SELECT SUM(Impressions) FROM Table WHERE Browser = ‘Firefox’
```
Firefox를 찾는데 걸리는 시간 O(n) -> O(1)
aggregation cost는 f(3)이다.

- Pre-aggregation
| Country |	Impressions |
| :------ | :---------- |
| CA | 	600 |
| MX | 	400 |
| USA |	1200 |

```
SELECT SUM(Impressions) FROM Table WHERE Country = ‘USA’
```
aggregation 값을 바로 얻을 수 있다.
아래와 같이 조건으로 쓰이는 dimension이 많아질 수 록 저장 공간을 많이 차지함
```
SELECT SUM(Impressions) FROM Table WHERE Country = ‘USA’ AND Browser = ‘Firefox’ AND Locale = ‘en’
```





- OLAP 비교 : https://medium.com/@leventov/comparison-of-the-open-source-olap-systems-for-big-data-clickhouse-druid-and-pinot-8e042a5ed1c7
- 질의 속도에 영향을 주는 기법들 : https://engineering.linkedin.com/blog/2019/06/star-tree-index--powering-fast-aggregations-on-pinot
- GraphQL : https://medium.com/devgorilla/what-is-graphql-f0902a959e4
