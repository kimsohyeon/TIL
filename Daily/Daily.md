# 2020-03-02
kafka record meta
https://stackoverflow.com/questions/54595952/how-to-ensure-messages-reach-kafka-broker

# 2020-02-29
https://blog.seulgi.kim/2014/04/actor-model-and-akka.html?m=1

# 2020-02-28
application 시작시에 parameter 하나를 받아서 globalsetting 인스턴스를 하나 생성하고 싶을때
```
class GlobalSettingInstance(actorSystem: ActorSystem) {
  val actor = actorSystem.actorOf(Props[TestActor], "testActor")
}

object GlobalSetting{
  var instance : Option[GlobalSettingInstance] = None
  def setInstance(actorSystem: ActorSystem ) = {
    if (instance.isEmpty) instance = Option(new GlobalSettingInstance(actorSystem))
  }
  def apply() = instance
}
```

- scala lock
  - 함수의 thread safe를 보장할 수 있다.
  - ReentrantLock = Mutex 다른 프로레스의 thread까지 thread safe 보장
  https://www.crocus.co.kr/1558
# 2020-02-22
- RocksDB
  - kafka로 데이터를 보내기 전에 데이터를 저장해서 안정성을 높일 수 있다.
  - key - value
  - HBase와 같은 lsm tree => write 속도가 높음
  - Queue로 활용하기
    - key를 만들 때, queue_id + sequence_id 조합으로 만들어서 여러개의 큐를 구현할 수 있다.
    - 여러개의 thread가 동시에 접근할 때 sync 하게 처리 할 수 있어야 된다.
    - 변경 이력이 많을 경우 seekToFirst는 빠르지 않다.  
    - Column Family를 두개로 나눠서 하나는 현재 인덱스 보관용, 하나는 데이터 큐로 사용한다. (서버 다운시 시작 지점 저장)

- hs_err_pid
  - JVM이 기록하는 크래시 로그
  - 스택 트레이스 정보 (stack trace)
  https://d2.naver.com/helloworld/1134732

# 2020-02-21
- RocksDB
  - database마다 filepath 필요
  - WriteBatch : 실행 이력 collection, disk에 넣기전까지의 기록을 가지고 있음, 사이즈 지정 가능
  - compress : filesystem에 데이터를 저장할때 압축해서 저장?


# 2020-02-20
- Kubernetes
https://kubernetes.io/ko/docs/concepts/overview/what-is-kubernetes/
  - master, worker, ingress
    - master : 클러스터 관리
    - worker : 컨테이너가 띄어져 있는 노드
    - ingress : 외부에서 컨테이너로 접근할 때 LB 역할
  - 호스트 OS의 커널을 공유해서 쓰고 필요한 lib/bin만 생성하기 때문에 가벼움
  ![vm-vs-docker](./images/vm-vs-docker.png)
  - kubernetes-cli로 클러스터에 접근 가능
- JNI
- RocksDB
queue처럼 사용 가능
플래쉬 드라이브, 메모리


# 2020-02-19
## #스트리밍플랫폼 #Jenkins #RocksDB #엔디언  
- 스트리밍 플랫폼
  - akka와 같은 스트리밍 플랫폼에서 graph 구조(vertex-edge)를 사용하는 경우가 있다.  
  https://doc.akka.io/docs/akka/current/stream/stream-graphs.html

- Jenkins  
CI/CD tool (Continuous integration (CI) and continuous delivery (CD))  
어떤 서비스에 코드 변경이 있을 때, quality와 functionality를 책임진다.   
예시
  - deployment    
  - git pull request를 적용할 때
  - feature branch의 변경사항을 master branch에 적용할 때  
  - unit test
    - 테스트용 데이터를 만들고 종료 후 삭제
  - schedule job (clone)
webhook으로 github와 연결 가능   
https://www.reddit.com/r/devops/comments/3vdemi/just_what_does_jenkins_do_anyway/  

- RocksDB  
key value store  
writing 속도가 빠름 fast storage
point lookup, range scan
LSM long structured merge tree

- 엔디언 (Endianness)
메모리와 같은 일차원 공간에 여러개의 연속된 대상을 배열하는 방법  
빅엔디언 (Big-endian) : 큰 단위 먼저
스몰엔디언 (Small-endian) : 작은 단위 먼저
![엔디언](./images/Endian.png)


# 2020-02-18
## #Grinder #ThreadLocal #동시성
- nGrinder : 서버 성능 측정 툴, Jython 사용
  - TPS : 초당 처리수
- ThreadLocal   
  일반적으로 변수의 수명은 해당 코드 블록에서 유효하다.  
  ThreadLocal을 사용하면 thread별로 변수를 선언할 수 있다.  
  예를 들어 ThreadLocal로 random int를 가지는 변수 a를 선언하면 thread마다 다른 int 값을 가질 수 있다.  
- 동시성  
  Scala와 같은 함수형 언어는 immutable 변수를 선호하기 때문에 다른 thread가 해당 변수에 동시 접근했을 때 문제가 없다.
  Jython, Python과 같은 경우 mutable 변수를 사용하면 thread safe를 보장할 수 없다.
  - thread safe : 동시에 여러 thread가 접근해도 안전성을 보장  
https://jythonbook-ko.readthedocs.io/en/latest/Concurrency.html  


- Json4s : Scala에서 사용하는 json 라이브러리
