ThisBuild / scalaVersion := "2.13.10"

ThisBuild / libraryDependencies ++= Seq(
  "io.github.galliaproject" %% "gallia-core"    % "0.4.0",
  "io.github.galliaproject" %% "gallia-spark"   % "0.4.0", // remember to provide a spark assembly JAR in ./lib to try this (~100MB); I used Spark 3.2.1 for the demo
  "io.github.galliaproject" %% "gallia-avro"    % "0.4.0",
  "io.github.galliaproject" %% "gallia-parquet" % "0.4.0")

