publishTo <<= (version) { v =>
  val repo = "http://127.0.0.1:8888/artifactory"
  if (v endsWith "SNAPSHOT")
    Some("snapshots" at s"${repo}/libs-snapshot-local")
  else
    Some("releases" at s"${repo}/libs-release-local")
}

publishMavenStyle := true

publishArtifact in Test := false
