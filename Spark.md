# Spark Tips

## 두개의 DataFrame 합치기 (zip two DataFrame)

    import org.apache.spark.sql.DataFrame
    import org.apache.spark.sql.Row
    import org.apache.spark.sql.types.{StructField, StructType, LongType}

    val a: DataFrame = sc.parallelize(Seq(("abc", 123), ("cde", 23))).toDF("column_1", "column_2")

    val b: DataFrame = sc.parallelize(Seq(Tuple1(1), Tuple1(2))).toDF("column_3")

    // Merge rows
    val rows = a.rdd.zip(b.rdd).map{
    case (rowLeft, rowRight) => Row.fromSeq(rowLeft.toSeq ++ rowRight.toSeq)}

    // Merge schemas
    val schema = StructType(a.schema.fields ++ b.schema.fields)

    // Create new data frame
    val ab: DataFrame = sqlContext.createDataFrame(rows, schema)
