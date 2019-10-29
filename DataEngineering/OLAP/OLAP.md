# 배경

기존에 사용하고 있는 ElasticSearch의 장단점을 살펴보고 대안이 될 수 있는 OLAP 후보군에 대해 알아보자

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

- OLAP VS OLTP
  | OLAP | OLTP |
  |:----:|:----:|
  | 의사결정을 위한 정보생성 | 일상적인 업무처리 |


• OLAP 비교 
	- https://medium.com/@leventov/comparison-of-the-open-source-olap-systems-for-big-data-clickhouse-druid-and-pinot-8e042a5ed1c7
• GraphQL
https://medium.com/devgorilla/what-is-graphql-f0902a959e4
