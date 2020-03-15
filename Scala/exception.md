# Exception 처리 방법

## try - catch 구문 

1. Option을 return 값으로 사용한다.
```
def method1(arg: String*): Option[List[String]] =
  try {
    Some(new MyClass(new URL(arg(0)))
        .map(x => x.getRawString.toString))
  } catch {
    case e: Exception => { e.printStackTrace(); None; }
  }
```

2. Try 클래스를 사용한다.
```
 sealed abstract class Try[+T]
 final case class Success[+T](value: T) extends Try[T]
 final case class Failure[+T](exception: Throwable) extends Try[T]
```
```
def method1(arg: String*): Try[List[String]] =
  try {
    Success(new MyClass(new URL(arg(0)))
        .map(x => x.getRawString.toString))
  } catch {
    case e: Exception => Failure(e)
  }
```
```
def method1(arg: String*): Try[List[String]] =
  Try { new MyClass(new URL(arg(0)))
        .map(x => x.getRawString.toString)) }
```
