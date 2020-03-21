# Iterator

list는 메모리에 모든 데이터가 올라가지만 Iterator는 데이터를 사용할 때 마다 메모리에 올라간다.
list를 return하는 함수는 메모리 오버플로우가 발생할 수 있다.
이런 경우에 list대신에 Iterator를 반환하면 된다.

```
val list = List(1, 2, 3, 4, 5, 6)
val it = list.iterator
```
