# Scala 동시성(Concurrent)

## val VS var
스칼라에서는 보통 immutable(val)을 쓰는 것을 권장한다.  
var 같이 상태마다 변하는 변수가 있으면, 비동기가 불가하다.  
예를 들어, 아래와 같은 코드는 순차적으로 처리하기 때문에 비효율적이다.
```
var sum = 0
for(i <- list) sum += j
```
toPar, folderLeft와 같은 비동기 코드를 짜면 처리 시간을 훨씬 줄일 수 있다.
```
val list : List[Int] = List(3, 6, 9)
val sum = list.foldLeft(0) (_+_))
```  

```
import scala.collection.par._ import scala.collection.par.Scheduler.Implicits.global

object Blitz extends App {
  val array = (0 until 100000).toArray

  // JVM JIT optimization 을 고려하여 충분히 해당 expression 들을 수행한뒤(1000번) body 를 수행
  val seqtime = warmedTimed(1000) { array.reduce(_ + _) }
  val partime = warmedTimed(1000) { array.par.reduce(_ + _) } val blitztime = warmedTimed(1000) { array.toPar.reduce(_ + _)}
}

// main: sequential time - 2.317
// main: parallel time - 1.031
// main: ScalaBlitz time - 0.046
```
참고자료 : [Scala 병렬 계산을 위한 Blitz library](https://starblood.tistory.com/entry/Scala-%EB%B3%91%EB%A0%AC-%EA%B3%84%EC%82%B0%EC%9D%84-%EC%9C%84%ED%95%9C-Blitz-library)
