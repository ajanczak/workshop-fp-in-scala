import Setup._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "workshop",
      scalaVersion := "2.12.4",
      version := "0.1.0-SNAPSHOT"
    )),
    libraryDependencies ++= dependencies
  )
