# API 개발

API 개발을 위해 기초적으로 알아야되는 사항을 정리한다.

## Micro Web FrameWork
API 개발에 앞서서 웹 프레임워크를 비교한다.  

1. echo server 테스트\
echo server : 클라이언트가 보낸 메세지를 받아서 클라이언트에게 다시 보내는(echo) 서버
2. Swagger 적용\
Swagger : API를 자동으로 문서화 해주는 프레임워크


web framework 리스트
- Play2
- akka http
- scalatra
- finatra



### Play2

#### Start new application
```
sbt new playframework/play-scala-seed.g8
```

#### Action
Play application은 대부분 Action으로 처리한다.

```
def action = Action { implicit request =>
  anotherMethod("Some para value")
  Ok("Got request [" + request + "]")
}
```

> Action VS WebSocket?

#### Echo Server & Swagger
새로운 Play2 애플리케이션을 시작하면 아래와 같은 디렉토리 구조가 만들어진다.\
[Play2 Anatomy](https://www.playframework.com/documentation/2.6.x/Anatomy)\
echo server를 개발하기 위해 고쳐야 되는 부분은 (dependency 부분 제외하고) [app/controllers](Play2_examples/app/controllers), [conf/routers](Play2_examples/conf/routers) 이다.

Swagger는 [Swagger Play2 Module](https://github.com/swagger-api/swagger-play/tree/master/play-2.7/swagger-play2)를 사용한다.\
controller 부분에 swagger api annotation을 붙이면 자동으로 swagger 문서에 API를 추가할 수 있다. \
추가로 [conf/application.conf](Play2_examples/conf/application.conf)에 문서 상단에 들어갈 info를 정의한다.


#### CORS Setting
Swagger을 UI상에서 확인하고 싶을떄는 아래와 같이 swagger url에 파라미터값으로 json을 넘긴다.\
https://petstore.swagger.io/?url=http://localhost:9000/swagger.json \
이때, CORS(Cross-Origin Resource Sharing)문제가 발생하면 [conf/application.conf](Play2_examples/conf/application.conf)에 CORS 설정을 해준다. \
*CORS : 도메인 또는 포트가 다른 서버의 자원을 요청하는 매커니즘

#### sbt packageBin
1. packager 가능하도록 JavaServerAppPacking 플러그인 추가
1. palyGenerateSecret으로 키 생성 : https://www.playframework.com/documentation/2.7.x/ApplicationSecret
1. 아래의 명령으로 압축된 zip 파일 만들기 (app/target/universal 폴더 확인)
```
sbt dataflow-api/universal:packageBin
```
2. unzip하고 서버 실행하기
```
unzip [zip 파일]
bin/dataflow-api
```



Play2 creating a new Application : https://www.playframework.com/documentation/2.7.x/NewApplication \
Play2 Action : https://www.playframework.com/documentation/2.7.x/ScalaActions \
Play2 CORS filter : https://www.playframework.com/documentation/2.7.x/CorsFilter
