// ===========================================================================
object Sd23 extends App {
  import scala.util.chaining._

  // ---------------------------------------------------------------------------
  import gallia._
  import gallia.spark._
  import gallia.parquet._

  // ---------------------------------------------------------------------------
  //val sc = new org.apache.spark.SparkContext("local", "sd23")

  // ---------------------------------------------------------------------------
  val schema  = cls("name".string, "age".int, "donor".boolean)
  val schema2 = cls("name".string, "phones".string, "cars".string)

  // ===========================================================================
  """{"name": "Bob", "age": 25, "donor": true}"""
    .read() // lazy
    .convertToMultiple
    .union(
      bobj("name" -> "Alice", "age" -> 26, "donor" -> true) )
    .increment("age")
    .write("/tmp/ppl.jsonl.gz")
    //.display()

  // ===========================================================================
  "/tmp/ppl.jsonl.gz"
    .stream()    
    //.pipe(sc.jsonLines(schema))
    //.streamAvro   ()
    //.streamParquet()

    .bringAll("/tmp/extra.tsv"
      //.pipe(sc.tsvWithHeader(schema2)) -- available in the next release, use below for now
      //.pipe(sc.tsvWithHeader(_)("name", "phones", "cars")))
        .stream())
        //, "name" <~> "name" /* if keys differed */)

    // ---------------------------------------------------------------------------
    .convert("phones", "cars").toRequired

    // ---------------------------------------------------------------------------
    .split("phones").by(";")

    // ---------------------------------------------------------------------------
    .deserialize1b("cars").withSplitters(";", ":").asNewKeys("model", "year", "color")

    // ---------------------------------------------------------------------------
    //.convert     ("cars" |> "year").toInt
    //.transformInt("cars" |> "year").using(_ - 2000)

    // ---------------------------------------------------------------------------
    .nest("phones", "cars").under("more")

    // ---------------------------------------------------------------------------
    .transformAllEntities("more" |> "cars").using { cars =>
      cars
        .transformString("year").using(_.toInt - 2000)
        .fuse("model", "color")
        //.fuse(_.string("model"), _.string("color"))
          .as("type").using { (m, c) => s"$m ${c.toUpperCase}"}
    }

    // ---------------------------------------------------------------------------
    .unnestAllFrom("more")

    // ---------------------------------------------------------------------------
    .groupBy("donor").as("donors")

    // ---------------------------------------------------------------------------
    //.rdd(_.coalesce(1, false)) // to by-pass gallia

    // ---------------------------------------------------------------------------
    //.display()
    //.writeAvro   ("/tmp/ppl.avro")
    //.writeParquet("/tmp/ppl.parq")
    .printPrettyJsons()
}


// ===========================================================================
