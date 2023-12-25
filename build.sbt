ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.3"

lazy val root = (project in file("."))
  .settings(
    name := "scala-with-cats"
  )

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Ypartial-unification"
)

libraryDependencies +=
  "org.typelevel" %% "cats-core" % "1.0.0"