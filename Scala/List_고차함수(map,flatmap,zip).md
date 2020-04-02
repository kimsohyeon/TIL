# List 활용

## List Array
- 차이점   
  - 리스트는 변경 불가능, 리스트 원소를 할당문으로 변경하지 못함  
  - 리스트의 구조는 재귀적(예 :연결 리스트), 배열은 평면적  
- 공통점
  - 리스트 배열 모두 속한 모든 원소의 타입은 동일

## List 생성
- 모든 리스트는 `Nil`과 `::`(콘즈)로 만듦
- `Nil`은 빈 리스트를 의미
- 중위 연산자 `::`은 리스트의 앞에 원소를 추가
- `x :: xs`는 첫번째 원소가 x이고 그 뒤에 xs리스트(의 원소들)가 오는 리스트를 나타냄
```
val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
```

## List 연결
```
List(1, 2) ::: List(3, 4, 5) // List(1,2,3,4,5)
```

## 길이 구하기
```
val a = List(1,2,3)
a.length // 3
a.isEmpty // a.length == 0 이 더 비쌈, length는 리스트의 끝을 찾기 위해 전체 리스트 순회
```

## 양 끝에 접근하기
```
val a = List(1,2,3,4)
a.head // 1
a.last // 4
a.init // List(1,2,3)
a.tail // List(2,3,4)
```

## 리스트 뒤집기
```
val a = List(1,2,3,4)
a.reverse // List(4,3,2,1)
```

## 접두사 / 접미사
`drop` / `take` / `splitAt`
```
val a = List(1,2,3,4)
a.take(2) // List(1,2)
a.drop(2) // List(3,4)
a.splitAt(2) // (List(1,2), List(3,4))
```

## apply / indices
```
val a = List(1,2,3,4)
a.apply(2) // 2 a(2)
a.indices // Range(0,1,2,3)
```

## flatten
```
val a = List(List(1,2), List(3))
a.flatten // List(1,2,3)
```

## zip / zipWithIndex / unzip
```
val a = List(1,2,3)
val b = List('a','b','c')
val c = a.zip(b) // List((1,'a'), (2,'b'), (3,'c'))
b.zipWithIndex // List(('a',0),('b',1),('c',2))
c.unzip // (List(1,2,3), List('a', 'b', 'c'))
```

## toString / mkString / addString
```
val a = List('a','b','c','d')
a.toString // List(a,b,c,d): mkString
a.mkString("[", ",", "]") // [a,b,c,d] 리스트 앞,중간,뒤에
들어갈 문자

val buf = new StringBuilder
a.addString(buf,"[", ",", "]") // [a,b,c,d,e]: StringBuilder
```

## iterator / toArray / copyToArray
```
// toArray
val a = List(1,2,3)
a.toArray // Array(1,2,3) arr.toList

// copyToArray
val arr = new Array[Int](10) // Array(0,0,0,0,0,0,0,0,0,0)
a copyToArray (arr, 3) // Array(0,0,0,1,2,3,0,0,0,0)

// iterator
val it = a.iterator
it.next // 1
it.next // 2
```

# List의 고차 매소드
## map & flatMap
```
val words = List("the", "brown", "fox")
words.map(_.toList) // List(List(t,h,e), List(b,r,o,w,n), List(f,o,x))
words.flatMap(_.toList) // List(t,h,e,b,r,o,w,n,f,o,x)
```

```
def toInt(s: String): Option[Int] = {
  try {
    Some(Integer.parseInt(s.trim))
  } catch {
    // catch Exception to catch null 's'
    case e: Exception => None
  }
}

val strings = Seq("1", "2", "foo", "3", "bar")
strings.map(toInt) // List(Some(1), Some(2), None, Some(3), None)
strings.flatMap(toInt) // List(1,2,3)
strings.flatMap(toInt).sum // 6
```
```
// List[List[Int]] => List[String]
val ints = List(List(1,2,3), List(4,5))
ints.flatMap(_.map(_.toString)) // List(1,2,3,4,5): List[String]
```

```
val map = Map(1 -> "one", 2 -> "two", 3 -> "three")
1 to map.size flatMap(map.get) // Vector(one, two, three)
```

## foreach
```
val n = List(1,2,3,4)
var sum = 0
n.foreach(sum += _) // sum = 10
```

## 리스트 걸러내기
`filter` / `partition` / `find` / `takeWhile` / `dropWhile` / `span`

```
List(1,2,3,4).filter(_ % 2 == 0) // List(2,4)
List(1,2,3,4).partition(_ % 2 == 0) // (List(2,4), List(1,3))
List(1,2,3,4).find(_ % 2 == 0) // Some(2) 첫번째 원소
List(1,2,3,-4,5).takeWhile(_ > 0) // List(1,2,3)
List(1,2,3,-4,5).dropWhile(_ > 0) // List(-4, 5)
List(1,2,3,-4,5)span(_ > 0) // (List(1,2,3), List(-4,5))
```

## forall / exists
```
List(1,2,3).forall(_ == 1) // false
List(1,2,3).exists(_ == 1) // true
```

## sortWith
```
val n = List(1,-3,4,2,6)
n.sortWith(_ < _) // List(-3,1,2,4,5)
```

## Reducing / Folding/ Scanning
### Reducing
```
List(1,7,2,9).reduceLeft(_ - _) // 1-7-2-9 = -17
List(1,7,2,9).reduceRight(_ - _) // 1-(7-(2-9)) = -13
```

### Folding
```
val numbers = List(1,7,2,9)
numbers.foldLeft(0)((m: Int, n: Int) => m + n)
```
```
// hook을 차례대로 적용할 수 있음
getHooks.foldLeft(df){(df, h) =>
  h.run(sparkSession, df)}
```
