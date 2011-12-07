
organization := "name.heikoseeberger.sbtproperties"

name := "sbtproperties"

// version is defined in version.sbt in order to support sbt-release

sbtPlugin := true

scalacOptions ++= Seq("-unchecked", "-deprecation")

publishTo <<= (version) { version =>
  def hseeberger(name: String) =
    Resolver.file("hseeberger-%s" format name, file("/Users/heiko/projects/hseeberger.github.com/%s" format name))(Resolver.ivyStylePatterns)
  val resolver =
    if (version endsWith "SNAPSHOT") hseeberger("snapshots")
    else hseeberger("releases")
  Option(resolver)
}

publishMavenStyle := false

seq(scalariformSettings: _*)

seq(scriptedSettings: _*)

seq(sbtrelease.Release.releaseSettings: _*)
