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
- 큰 데모 데이터(성연령)와 실시간성 데이터(작은 사이즈)의 데이터를 조인할때는 OLAP가 유용하다. 


- OLAP 비교 : https://medium.com/@leventov/comparison-of-the-open-source-olap-systems-for-big-data-clickhouse-druid-and-pinot-8e042a5ed1c7
- GraphQL : https://medium.com/devgorilla/what-is-graphql-f0902a959e4
