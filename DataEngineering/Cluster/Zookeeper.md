# ZooKeeper

## CentOS에 Zeekeeper standalone으로 설치하기

java JDK 설치
```
sudo yum install java
```

apache zookeeper 다운로드 (소스파일 말고 bin 파일 받는게 편함)
```
wget http://apache.mirror.cdnetworks.com/zookeeper/zookeeper-3.5.6/apache-zookeeper-3.5.6-bin.tar.gz
tar -xzf apache-zookeeper-3.5.6-bin.tar.gz
ln -s http://apache.mirror.cdnetworks.com/zookeeper/zookeeper-3.5.6/apache-zookeeper-3.5.6-bin.tar.gz zookeeper
```

설정파일 작성
```
cd zookeeper
vim /conf/zoo.cfg
```

```
#zoo.cfg
tickTime=2000
dataDir=/home/deploy/zookeeper/data
clientPort=2181
```

데이터 폴더 만들기
```
mkdir data
```


zookeeper 실행
```
/bin/zkServer.sh start
/bin/zkServer.sh status
```



## 참고자료
- zookeeper getting started guide : https://zookeeper.apache.org/doc/r3.1.2/zookeeperStarted.html
- zookeeper 클러스터 설치 : https://jdm.kr/blog/214
