lazy val root = (project in file(".")).settings(
  name := "hands-on-scala",
  scalaVersion := "3.3.0"
)

// sbt = DSL (domain-specific language )
// sbt is a scala build tool (compile, run, test, manage library dependencies)

// just run the command "sbt" at project root => starts a sbt server
// then "run" to run the app

// templates for starting a new app https://github.com/scala/scala3.g8

// structure "src/main/scala" => useful for projects that also run other java-based lgues