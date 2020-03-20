# Scala Tips

## trait, abstract class
한 클래스는 하나의 클래스만 상속 받을 수 있다.   
mixin은 trait를 이용한다.

```
trait T1
trait T2
class P1
class P2

class C1 extends T1
class C2 extends T1 with T2
class C3 extends T2 with T1
class C4 extends P1 with T1
/// class C5 extends T1 with P1 // invalid
/// class C6 extends P1 with P2 // invalid
```
https://stackoverflow.com/questions/41031166/scala-extends-vs-with


- abstract class
    - abstract method, non-abstract method 둘 다 정의 가능
    - 다중 상속 지원하지 않음
- trait
    - abstract method, non-abstract method 둘 다 정의 가능
    - 다중 상속 지원
    - 인스턴스에 추가 가능
    ```
    class DavidBanner

    trait Angry {
      println("You won't like me ...")
    }

    object Test extends App {
      val hulk = new DavidBanner with Angry
    }
    ```
    - constructor parameter 정의 안됨  



## Multiple Parameter Lists
여러개의 괄호를 사용해서 함수를 정의하면 좀 더 심플하게 함수를 사용할 수 있다.
1. fluent API
함수 파라미터를 사용할 때
```
def unless(exp: Boolean)(f: => Unit): Unit = if (!exp) f
unless(x < 5) {
  println("x는 5보다 작지 않다.")
}
```
2. Implicit Parameters
파라미터 중 하나만 implicit를 지정하고 싶을 때
```
def foo(a: Int)(b: Implicit Int): Int
```
3. For type inference
type inference를 사용할 때
```
def foldLeft[B](z: B)(op: (B, A) => B): B
List("").foldLeft(0)(_ + _.length)
```
```
def foldLeft[B](z: B, op: (B, A) => A): B
List("").foldLeft[Int](0, _ + _.length)
List("").foldLeft(0, (b: Int, a: String) => b + a.length)
```

참고자료 : https://docs.scala-lang.org/style/declarations.html


## val def
Scala에서는 val, var, def, lazy val로 명명 가능하다.

val - 정의될 때 실행
lazy val - 처음 사용될 때 실행  
def - 사용될 때 마다 실행

참고자료 : https://www.holaxprogramming.com/2017/11/18/scala-value-naming/


## Singleton and Companion Object
스칼라는 자바와 달리 static 키워드가 없다.  
대신, singleton object를 만들어서 entry point로 사용한다.


```
object Singleton{  
    def main(args: Array[String]){  
        SingletonObject.hello()     // No need to create object.  
    }  
}  


object SingletonObject{  
    def hello(){  
        println("Hello, This is Singleton Object")  
    }  
}
```

하나의 소스파일에 같은 이름의 class와 object를 정의   
companion object - companion class는 같은 클래스를 인스턴스 부분과 static 부분으로 분리해둔 것

```
class Dog(name: String) {
  def bark = println("bark! bark!")
  def getName = name
}

object Dog {
  def apply(name: String) = new Dog(name)
}
```
참고자료 :   
https://www.javatpoint.com/scala-singleton-and-companion-object  
https://partnerjun.tistory.com/11
