organization in ThisBuild := "thirstycrow"

scalaVersion in ThisBuild := "2.11.7"

crossScalaVersions := Seq("2.10.5")

incOptions := incOptions.value.withNameHashing(true)

shellPrompt := { state =>
  "%s> ".format(Project.extract(state).currentProject.id)
}

javaOptions ++= Seq(
  "-Djava.net.preferIPv4Stack=true"
)

lazy val root = (project in file("."))
  .aggregate(backend, frontend)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val myapp = (crossProject in file("."))
  .jvmSettings(revolverSettings: _*)
  .jvmSettings(
    libraryDependencies ++= Seq(
      lib.finatra.http,
      lib.finatra.thrift,
      lib.finatra.slf4j,
      lib.finagle.mysql,
      lib.finagle.redis
    )
  )
  //.jsSettings(workbenchSettings: _*)
  .jsSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "scalarx" % lib.v.scalarx,
      "com.lihaoyi" %%% "scalatags" % lib.v.scalatags
    ),
    scalaJSStage in Global := FastOptStage,
    artifactPath in (Compile, fastOptJS) := {
      val jsApp = (artifactPath in (Compile, fastOptJS)).value
      (classDirectory in Compile).value / "public" / jsApp.name
    }
    //bootSnippet := "myapp.MyApp().main();"
  )

lazy val backend = myapp.jvm
  .dependsOn(frontend)

lazy val frontend = myapp.js

import spray.revolver.Actions._
import spray.revolver.RevolverPlugin._
import spray.revolver.RevolverPlugin.Revolver._
lazy val revolverSettings = Revolver.settings ++ Seq(
  enableDebugging(port = 5006, suspend = false),
  mainClass in reStart := Some("myapp.backend.MyAppServer"),
  reStartArgs := Seq("-doc.root=public")
)
