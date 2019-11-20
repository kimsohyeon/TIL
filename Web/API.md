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
- lagom
- finatra
- scalatra


### Play2

#### Start new application
```
sbt new playframework/play-scala-seed.g8
```

#### Action
Play application은 대부분 Action으로 처리한다.\

```
def action = Action { implicit request =>
  anotherMethod("Some para value")
  Ok("Got request [" + request + "]")
}
```

> Action VS WebSocket?

#### Echo Server
새로운 Play2 애플리케이션을 시작하면 아래와 같은 디렉토리 구조가 만들어진다.
https://www.playframework.com/documentation/2.6.x/Anatomy
echo server를 개발하기 위해 고쳐야 되는 부분은 (dependency 부분 제외하고) app/controllers, conf/routers 이다. \







Play2 creating a new Application : https://www.playframework.com/documentation/2.7.x/NewApplication \
Play2 Action : https://www.playframework.com/documentation/2.7.x/ScalaActions
