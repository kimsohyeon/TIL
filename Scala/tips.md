# Scala Tips

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
