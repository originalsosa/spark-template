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

    ivyScala := ivyScala.value.map {_.copy(overrideScalaVersion = true)},

    fork in run := true,

  libraryDependencies ++= { sparkEnv.value match {
    case SparkEnv.CDH => cdhProvidedDeps map ( _ % "provided")
    case SparkEnv.OPENSOURCE => providedDeps map ( _ % "provided")
  }}

  )
/*
  val osSettings = inConfig(OS)(baseSettings) ++ Seq(
    libraryDependencies in OS ++= providedDeps map (_ % "provided")
  )

  val cdhSettings = inConfig(CDH)(Seq(
    libraryDependencies in CDH ++= cdhProvidedDeps map (_ % "compile -> cdh")
  ))
*/
}
