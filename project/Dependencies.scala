import sbt._
import Library._

object Version {
  final val Scala     = "2.11.8"
  final val ScalaTest = "3.0.1"
  final val Spark       = "2.1.0"
  final val SparkTesting = "2.1.0_0.6.0"
  final val Hadoop    = "2.6.0"
  final val TypesafeConfig = "1.3.1"
  final val Logback   = "1.2.3"
  final val Slf4J     = "1.7.24"
}

object Library {

  val scalactic         =     "org.scalactic" %% "scalactic" % Version.ScalaTest
  val scalaTest         =     "org.scalatest" %% "scalatest" % Version.ScalaTest

  val sparkCore         =     "org.apache.spark" %% "spark-core" % Version.Spark
  val sparkStreaming    =     "org.apache.spark" %% "spark-streaming" % Version.Spark
  val sparkSql          =     "org.apache.spark" %% "spark-sql" % Version.Spark
  val sparkHive         =     "org.apache.spark" %% "spark-hive" % Version.Spark
  val sparkTesting      =     "com.holdenkarau" %% "spark-testing-base" % Version.SparkTesting

  val hadoopYarnAPI     =     "org.apache.hadoop" % "hadoop-yarn-api" % Version.Hadoop
  val hadoopYarnClient  =     "org.apache.hadoop" % "hadoop-yarn-client" % Version.Hadoop
  val hadoopYarnCommon  =     "org.apache.hadoop" % "hadoop-yarn-common" % Version.Hadoop
  val hadoopYarnApps    =     "org.apache.hadoop" % "hadoop-yarn-applications-distributedshell" % Version.Hadoop
  val hadoopYarnServer  =     "org.apache.hadoop" % "hadoop-yarn-server-web-proxy" % Version.Hadoop
  val hadoopClient      =     "org.apache.hadoop" % "hadoop-client" % Version.Hadoop

  val slf4j             =     "org.slf4j" % "slf4j-api" % Version.Slf4J
  val logback           =     "ch.qos.logback" % "logback-classic" % Version.Logback

  val jodaConvert       =     "org.joda" % "joda-convert" % "1.8.1"
  val typesafeConfig    =     "com.typesafe" % "config" % Version.TypesafeConfig
}

object Resolvers {

  val resolvers = Seq(
    Resolver sonatypeRepo "public",
    Resolver typesafeRepo "releases",
    Resolver bintrayRepo("hseeberger", "maven"),
    Resolver bintrayRepo("lonelyplanet", "maven"))
}

object Dependencies {

  val scalaVersionUsed = Version.Scala

  val commonResolvers = Resolvers.resolvers

  val commonTestDeps = Seq(scalactic, scalaTest)

  val analyticsProvidedDeps = 
    Seq(sparkCore, sparkSql, sparkHive,
        hadoopYarnAPI, hadoopYarnClient, hadoopYarnCommon, hadoopYarnApps, hadoopYarnServer, hadoopClient,
        slf4j, logback, typesafeConfig)

  val analyticsOtherDeps = Seq( jodaConvert )

  val analyticsTestDeps = commonTestDeps ++ Seq(sparkTesting)

}
