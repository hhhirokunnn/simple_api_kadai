name := """simple_api_kadai"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  jdbc,
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test,
// https://mvnrepository.com/artifact/org.scalikejdbc/scalikejdbc
  "org.scalikejdbc" %% "scalikejdbc" % "3.3.5",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.3.5",
  // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
  "org.slf4j" % "slf4j-simple" % "1.7.28" % Test,
  // https://mvnrepository.com/artifact/org.scalikejdbc/scalikejdbc-play-initializer
  "org.scalikejdbc" % "scalikejdbc-play-initializer_2.12" % "2.6.0",
  // https://mvnrepository.com/artifact/mysql/mysql-connector-java
  "mysql" % "mysql-connector-java" % "8.0.17",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.0.6",
  "com.typesafe.play" %% "play-ws-standalone-json" % "2.0.6",
  // https://mvnrepository.com/artifact/net.liftweb/lift-json_2.12/3.3.0
  "net.liftweb" % "lift-json_2.12" % "3.3.0"
)
