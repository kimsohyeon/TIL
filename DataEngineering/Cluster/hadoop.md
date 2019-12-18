https://dzone.com/articles/an-introduction-to-hdfs

![HDFS Master/slave Architecture](../참고자료/이미지/HDFS_master_slave_architecture.png)
> HDFS는 NN(name node), DN(data node), Secondary NameNode로 구성된다.



[빅데이터 - 하둡, 하이브로 시작하기](https://wikidocs.net/book/2203)  
맵의 입력은 스플릿(InputSplit)단위로 분할된다.
적절한 스플릿 크기는 데이터 지역성의 이점을 얻을 수 있는 HDFS 블록의 기본 크기(128MB)이다.

![맵 작업 데이터 지역성](../참고자료/이미지/맵_작업_데이터_지역성.png)
