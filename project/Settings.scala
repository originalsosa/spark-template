import sbt._
import sbt.Keys._
import SparkEnvPlugin.autoImport._

object Settings {
  import Dependencies._
  import Configs._

val baseSettings = Seq(
    organization := "me.rpritchett",
    version := "0.0.1",

    scalaVersion := scalaVersionUsed,

    scalacOptions ++= Seq(
      "-target:jvm-1.7",
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:postfixOps",
      "-Xlint"
    ),

    javacOptions ++= Seq(
      "-source", "1.7",
      "-target", "1.7",
      "Xlint"
    ),

    resolvers ++= commonResolvers,

    fork in run := true,
    fork in test := true,
   
    parallelExecution in test := false,

    libraryDependencies ++= {
     sparkEnv.value match {
       case SparkEnv.CDH =>
         cdhDependencies()

       case SparkEnv.OPENSOURCE =>
         openSourceDependencies()

     }
   }
  )

  def cdhDependencies() = {
    val provided = CDHDependencies.analyticsProvidedDeps map ( _ % "provided" )
    val test = CDHDependencies.analyticsTestDeps map ( _ % "test" )
    provided ++ test ++ CDHDependencies.analyticsOtherDeps
  }

  def openSourceDependencies() = {
    val provided = Dependencies.analyticsProvidedDeps map ( _ % "provided" )
    val test = Dependencies.analyticsTestDeps map ( _ % "test" )
    provided ++ test ++ Dependencies.analyticsOtherDeps
  }

}
