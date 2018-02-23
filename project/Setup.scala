import sbt._

object Setup {

  object Versions {
    val cats = "1.0.1"
    val scalaTest = "3.0.4"
  }

  lazy val dependencies = Seq(
    "org.scalatest" %% "scalatest" % Versions.scalaTest,
    "org.typelevel" %% "cats-core" % Versions.cats
  )

}
