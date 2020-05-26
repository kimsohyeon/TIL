- case class: 패턴 매치, 데이터 모델링에 사용
- case object: akka actor message로 사용
http://hello-scala.com/412-case-objects.html
- object: singleton 패턴, utility성 함수를 만들때 사용

```
object FileUtils {
    def readTextFileAsString(filename: String): Try[String] = ...
}
```
- Try: exception이 발생할 여지가 있는 코드를 Try {} 형태로 묶음. match 문을 통해 Success, Failure 케이스에 대한 코드를 작성, toOption: Success => Some(), Failure => None
