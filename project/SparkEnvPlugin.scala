import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

/** sets the spark environment */
object SparkEnvPlugin extends AutoPlugin {

  // make sure it triggers automatically
  override def trigger = AllRequirements
  override def requires = JvmPlugin

  object autoImport {
    object SparkEnv extends Enumeration {
      val CDH, OPENSOURCE = Value
    }

    val sparkEnv = settingKey[SparkEnv.Value]("the current Spark environment")
  }
  import autoImport._

  override def projectSettings: Seq[Setting[_]] = Seq(
    sparkEnv := {
      sys.props.get("env")
        .orElse(sys.env.get("SPARK_ENV"))
        .flatMap {
          case "cdh" => Some(SparkEnv.CDH)
          case "opensource" => Some(SparkEnv.OPENSOURCE)
          case unkown => None
        }
        .getOrElse(SparkEnv.OPENSOURCE)
    },
    // give feed back
    onLoadMessage := {
      // depend on the old message as well
      val defaultMessage = onLoadMessage.value
      val env = sparkEnv.value
      s"""|$defaultMessage
          |Running in spark environment: $env""".stripMargin
    }
  )

}