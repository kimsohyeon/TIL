# Java8의 람다(Lambda)

#### 참고자료
[Java8에서 람다(Lambda) 정리](https://yookeun.github.io/java/2018/01/19/java-lambda1/)  
[Java 8 vs. Scala: Part I](https://dzone.com/articles/java-8-%CE%BBe-vs-scalapart-i)

람다는 함수형 인터페이스를 통해서만 사용 가능   
함수형 인터페이스는 하나의 추상 메소드만 지닌 인터페이스

```
//Before Java 8
Runnable r = new Runnable(){  
  public void run(){    
    System.out.println(“This should be run in another thread”);  
  }
};

//Java 8
Runnable r = () -> System.out.println(“This should be run in another thread”);
```

```
@FunctionalInterface
public interface BookPredicate {
  boolean check(Book book); // 한 개의 메소드만 존재
}

public static List<Book> collectBook(List<Book> bookList, bookPredicate p) {
  List<Book> result = new ArrayList<>();
  for (Book book : bookList){
    if (p.check(book)) {
      result.add(book);
    }
  }
  return result;
}

List<Book> book1 = collectBook(bookList, (Book book) -> book.getPrice() < 11000);
List<Book> book2 = collectBook(bookList, (Book book) -> "history".equals(book.getType()));
```


java와 달리 scala의 lamba는 type을 explicit하게 지정하거나 함수형 인터페이스를 정의할 필요가 없다.
따라서 scala는 간편하게 람다 함수를 파라미터로 사용할 수 있다.
```
//Java 8
Function<String, Integer> parseInt = s -> Integer.parseInt(s);

//Scala
val parseInt = (s: String) => s.toInt
```


```
//Java 8
@FunctionalInterface
interface PentFunction<A, B, C, D, E, R> {  
  public R apply(A a, B b, C c, D d, E e);
}
PentFunction<String, Integer, Double, Boolean, String, String> sumOfFive = (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5;

//Scala
val sumOfFive = (i1: String, i2: Int, i3: Double, i4: Boolean, i5: String) => i1 + i2 + i3 + i4 + i5;
```
