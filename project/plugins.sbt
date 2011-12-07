
resolvers ++= Seq(
  "gseitz@github" at "http://gseitz.github.com/maven/"
)

addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.4")

addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.3.0")

libraryDependencies <+= (sbtVersion)(sbtVersion =>
  "org.scala-tools.sbt" %% "scripted-plugin" % sbtVersion
)
