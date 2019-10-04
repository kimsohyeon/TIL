# Spark Tips

## 두개의 DataFrame 합치기 (zip two DataFrame)

*df의 partition수가 같아야 한다.*

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


https://stackoverflow.com/questions/32882529/how-to-zip-two-or-more-dataframe-in-spark



## RDD를 DataFrame으로 변환 (Convert RDD to DataFrame)

*DataFame를 정의하는 case class는 df를 변환하는 내용과 같은 scope(notebook cell)에 정의되면 안된다.*

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


https://stackoverflow.com/a/33129561/5867255

https://stackoverflow.com/a/36056208/5867255
