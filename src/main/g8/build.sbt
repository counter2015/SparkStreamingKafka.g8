import Dependencies._

ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.11.10"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file(".")).
  settings(
    name := "kafka-streaming",
    assemblySettings,
    assemblyJarName in assembly := "kafka-sparkstreaming.jar",
    libraryDependencies ++= Seq(
      spark,
      sparkStreamingKafka,
      scalaLogging,
      typesafeConfig
    )   
    
  )

lazy val assemblySettings = Seq(
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs@_*) => MergeStrategy.discard
    case PathList("org", "apache", "spark", xs@_*) => MergeStrategy.first
    case "application.conf" => MergeStrategy.concat
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)
