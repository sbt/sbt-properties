
addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.6")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.1")

libraryDependencies <+= (sbtVersion)("org.scala-sbt" % "scripted-plugin" % _)
