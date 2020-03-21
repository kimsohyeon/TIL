# Scala Future

## Future
Future는 비동기적으로 처리된다.
Future는 미래에 사용 가능한 값을 포함하는 placeholder 이다.
```
object FuturesComputation extends App {
  import scala.concurrent._
  import ExecutionContext.Implicits.global

  Future {
    log(s"the future is here")
  }

  log(s"the future is coming")
}
```
Future는 결과값/exception을 랩핑하고 있다.
```
\\ Got it 프린트되지 않음
try { Future(throw new RuntimeException(""))} catch { case ex => println("Got it") }
\\ Got it 프린트됨
\\ None일 때 Exception에 따라 값을 반환해 복구하는 recover 함수
Future(throw new RuntimeException("")).recover { case ex => println("Got it") }
```

### Future와 콜백
Future의 콜백 처리는 foreach를 통해서 할 수 있다.
```
val urlSpec: Future[Seq[String]] = getUrlSpec()
urlSpec foreach {
lines => log(s"Found occurrences of 'password'\n${find(lines, "password")}\n")
}
```

### Future와 예외처리
onComplete  
```
urlSpec onComplete {
  case Success(text) => log(text)
  case Failure(t) => log(s"Failed due to $t")
}
```

### Future와 합성

#### map
map 은 기존 퓨처에 있는 값을 사용해서 새 퓨처를 만드는 메소드

```
val buildFile = Future { Source.fromFile("build.sbt").getLines }

val longestBuildLine = buildFile.map(lines => lines.maxBy(_.length))

longestBuildLine onComplete {
case Success(line) => log(s"the longest line is '$line'")
}
```


#### flatMap
두개의 Future를 연결해서 새로운 Future을 만듬
```
val netiquette = Future { Source.fromURL("http://www.ietf.org/rfc/rfc1855.txt").mkString }
val urlSpec = Future { Source.fromURL("http://www.w3.org/Addressing/URL/url-spec.txt").mkString }
val answer = netiquette.flatMap { nettext =>
  urlSpec.map { urltext =>
    "First, read this: " + nettext + ". Now, try this: " + urltext
  }
}

answer foreach {
  case contents => log(contents)
}
```


#### for comprehension
```
val gitignoreFile = Future { Source.fromFile(".gitignore-SAMPLE").getLines }

val longestGitignoreLine = for (lines <- gitignoreFile) yield lines.maxBy(_.length)

longestBuildLine onComplete {
  case Success(line) => log(s"the longest line is '$line'")
}
```


#### async - await
```
import scala.concurrent.ExecutionContext.Implicits.global  
import scala.async.Async._

val gitignoreFile1: Future[Int] = ...  
val gitignoreFile2: Future[Int] = ...

val asyncComputation = async {  
    await(gitignoreFile1) + await(gitignoreFile2)

}
```


### Future.sequence
List[Future[T]] => Future[List[T]]


```
val x1 = Future {
  Thread.sleep(2000)
  println("x1 is completed")
  "Hello x1"
}

val x2 = Future {
  Thread.sleep(4000)
  println("x2 is completed")
  throw new Exception("Exception occured with x2")
}

val x3 = Future {
  Thread.sleep(10000)
  println("x3 is completed")
  "Hello x3"
}

val x = Future.sequence(List(x1, x2, x3))
```



참고 자료 : https://hamait.tistory.com/763?category=79134  
https://stackoverflow.com/questions/18960339/clarification-needed-about-futures-and-promises-in-scala
