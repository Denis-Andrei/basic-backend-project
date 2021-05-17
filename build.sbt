name := """basic-backend-project"""
organization := "example.com"

version := "1.0-SNAPSHOT"


lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(resolvers ++= Seq(
    Resolver.bintrayRepo("hmrc", "releases"),
    Resolver.jcenterRepo
  ))

scalaVersion := "2.13.5"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.28.2" % Test

//libraryDependencies += "uk.gov.hmrc" %% "hmrctest" % "3.9.0-play-26" % Test


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "example.com.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "example.com.binders._"
