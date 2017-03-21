name := """play-mqtt"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.sorm-framework" % "sorm" % "0.3.18",
  "com.h2database" % "h2" % "1.4.187",
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "bootstrap" % "3.3.5",
  "com.google.inject" % "guice" % "4.0",
  "org.eclipse.paho" % "org.eclipse.paho.client.mqttv3" % "1.0.2",
  specs2 % Test
)

dependencyOverrides += "org.scala-lang" % "scala-compiler" % scalaVersion.value

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "MQTT Repository" at "https://repo.eclipse.org/content/repositories/paho-releases/"
)

routesGenerator := InjectedRoutesGenerator

fork in run := true

LessKeys.compress in Assets := true

pipelineStages := Seq(digest)

includeFilter in (Assets, LessKeys.less) := "*.less"

excludeFilter in (Assets, LessKeys.less) := "_*.less"
