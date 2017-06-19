import sbt._
import CDHLibrary._

object CDHVersion {
  final val Scala     = "2.11.8"
  final val ScalaTest = "3.0.1"
  final val Spark       = "2.1.0-cdh5.11.1"
  final val SparkTesting = "2.1.0_0.6.0"
  final val Hadoop    = "2.6.0-cdh5.11.1"
  final val TypesafeConfig = "1.3.1"
  final val Logback   = "1.2.3"
  final val Slf4J     = "1.7.24"
}

object CDHLibrary {

  val scalactic         =     "org.scalactic" %% "scalactic" % CDHVersion.ScalaTest
  val scalaTest         =     "org.scalatest" %% "scalatest" % CDHVersion.ScalaTest

  val sparkCore         =     "org.apache.spark" %% "spark-core" % CDHVersion.Spark
  val sparkStreaming    =     "org.apache.spark" %% "spark-streaming" % CDHVersion.Spark
  val sparkSql          =     "org.apache.spark" %% "spark-sql" % CDHVersion.Spark
  val sparkHive         =     "org.apache.spark" %% "spark-hive" % CDHVersion.Spark
  val sparkTesting      =     "com.holdenkarau" %% "spark-testing-base" % CDHVersion.SparkTesting

  val hadoopYarnAPI     =     "org.apache.hadoop" % "hadoop-yarn-api" % CDHVersion.Hadoop
  val hadoopYarnClient  =     "org.apache.hadoop" % "hadoop-yarn-client" % CDHVersion.Hadoop
  val hadoopYarnCommon  =     "org.apache.hadoop" % "hadoop-yarn-common" % CDHVersion.Hadoop
  val hadoopYarnApps    =     "org.apache.hadoop" % "hadoop-yarn-applications-distributedshell" % CDHVersion.Hadoop
  val hadoopYarnServer  =     "org.apache.hadoop" % "hadoop-yarn-server-web-proxy" % CDHVersion.Hadoop
  val hadoopClient      =     "org.apache.hadoop" % "hadoop-client" % CDHVersion.Hadoop

  val slf4j             =     "org.slf4j" % "slf4j-api" % CDHVersion.Slf4J
  val logback           =     "ch.qos.logback" % "logback-classic" % CDHVersion.Logback

  val typesafeConfig    =     "com.typesafe" % "config" % CDHVersion.TypesafeConfig
}
/*
object Resolvers {

  val resolvers = Seq(
    Resolver sonatypeRepo "public",
    Resolver typesafeRepo "releases",
    Resolver bintrayRepo("hseeberger", "maven"),
    Resolver bintrayRepo("lonelyplanet", "maven"))
}*/

object CDHDependencies {

  val scalaVersionUsed = CDHVersion.Scala

  val commonResolvers = Resolvers.resolvers

  val commonTestDeps = Seq(scalactic, scalatest)

  val analyticsProviderDeps = 
    Seq(sparkCore, sparkSql, sparkHive,
        hadoopYarnAPI, hadoopYarnClient, hadoopYarnCommon, hadoopYarnApps, hadoopYarnServer, hadoopClient,
        slf4j, logBack, typesafeConfig)

  val analyticsOtherDeps = Seq( jodaConvert )

  val analyticsTestDeps = commonTestDeps ++ Seq(sparkTesting)

}
