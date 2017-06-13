import sbt._

object Configs {

  val OS = config("os") extend(Compile)
	val CDH = config("cdh") extend(Compile)
	val all = Seq(OS, CDH)
}