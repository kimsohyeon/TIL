# Spark Tips

## Spark Serializable
[참고 블로그](https://12bme.tistory.com/436)\
[serializable challenbes with spark and scala](https://medium.com/onzo-tech/serialization-challenges-with-spark-and-scala-a2287cd51c54)\
Spark API에서 제공하는 데이터형식 RDD, Datafame, Dataset에 적용하는 함수는 executor가 데이터의 각 세그먼트를 처리하기 때문에 직렬화가능(serializable)해야 된다.\
따라서 Spark API 에서 제공하는 함수(filter, map, ...)안에서 사용되는 클래스도 직렬화가능(serializable)해야된다.\
예를 들어, dataframe은 serializable하지 않기 때문에 df1.rdd.map{ ... df2 ... } 와 같이 사용할 수 없다.

또한, 드라이버 프로그램에서 정의한 변수를 Spark API 함수에서 변경해도 executor가 처리하기 떄문에 드라이버 프로그램에서는 변경한 내용을 볼 수 없다.

## mapPartitions()
[참고 stackoverflow](https://stackoverflow.com/a/39203798/5867255)

```
def map[U: ClassTag](f: T => U): RDD[U]
```

```
def mapPartitions[U: ClassTag](f: Iterator[T] => Iterator[U], preservesPartitioning: Boolean = false): RDD[U]
```

heavyweight initialization이 있을 경우는 mapPartition을 쓰는 것이 좋다.\
예를 들어서 DB 커넥션을 한번만 맺어서 처리할때는 map보다는 mapPartition을 사용.\
map은 row 각각을 변환하고 mapPartition은 partition(row set)을 변환한다.

```
val newRd = myRdd.mapPartitions(partition => {
  val connection = new DbConnection /*creates a db connection per partition*/

  val newPartition = partition.map(record => {
    readMatchingFromDB(record, connection)
  }).toList // consumes the iterator, thus calls readMatchingFromDB

  connection.close() // close dbconnection here
  newPartition.iterator // create a new iterator
})
```


## 두개의 DataFrame 합치기 (zip two DataFrame)
 *join, zip은 연산량이 많기 때문에 되도록 피하는 것이 좋다!!*

 *join key가 없는 상태에서 순서대로 두 dataframe을 합치기*

* 삽질  
  1. rdd zip()을 쓰기 위해서는 각 rdd의 partition의 element 수가 같아야 한다.

    zip()사용시에 repartition(), coalesce() 함수로도 element수를 맞추지 못함

    ```
    import org.apache.spark.sql.DataFrame
    import org.apache.spark.sql.Row
    import org.apache.spark.sql.types.{StructField, StructType, LongType}

    val a: DataFrame = sc.parallelize(Seq(("abc", 123), ("cde", 23))).toDF("column_1", "column_2").repartition(100)

    val b: DataFrame = sc.parallelize(Seq(Tuple1(1), Tuple1(2))).toDF("column_3").repartition(100)

    // Merge rows
    val rows = a.rdd.zip(b.rdd).map{
    case (rowLeft, rowRight) => Row.fromSeq(rowLeft.toSeq ++ rowRight.toSeq)}

    // Merge schemas
    val schema = StructType(a.schema.fields ++ b.schema.fields)

    // Create new data frame
    val ab: DataFrame = sqlContext.createDataFrame(rows, schema)
    ```

  2. auto increment한 임의의 id를 만들어서 join key로 사용하려고 했으나, 실패

    아래와 같이 할 경우, 두 dataframe의 row 수가 같아도 다른 id 값이 만들어짐, id 순서보장 못함

    ```
    import org.apache.spark.sql.functions._
    a = a.withColumn("id",monotonicallyIncreasingId)
    b = b.withColumn("id",monotonicallyIncreasingId)
    val result = a.join(b, Seq("id"), "left_outer")
    ```


https://stackoverflow.com/a/32884253/5867255
https://brocess.tistory.com/183


## RDD를 DataFrame으로 변환 (Convert RDD to DataFrame)

*DataFame를 정의하는 case class는 df를 변환하는 내용과 같은 scope(notebook cell)에 정의되면 안된다.*

```
// DataFrame 구조 정의
// df를 변환하는 내용과 같은 scope에 정의되면 안된다.  
case class X(query:String, docs: List[Tuple2[String, Double]])

import org.apache.spark.sql.Row

// df.rdd.map{ case Row() => X(a,b,c)} 와 같은 형태로 진행된다.
val result = new_api_docs.rdd.map {
case Row(ds, g, m, query, qc) => {
    var value = ds.asInstanceOf[List[Map[String, Any]]].map( x => (x.get("dsid").get.asInstanceOf[String], x.get("score").get.asInstanceOf[Double] ))
    X(query.toString, value)
  }
}.toDF()
```

https://stackoverflow.com/a/33129561/5867255

https://stackoverflow.com/a/36056208/5867255
