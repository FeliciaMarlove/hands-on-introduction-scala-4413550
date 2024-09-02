@main def sayHello: Unit = sayHi("Florence")

// Scala2:
//def sayHi(name: String): Unit = 
//  println(s"Hi $name")
//end sayHi

// New in Scala3:
//def sayHi(name: String): Unit = {
//  println(s"Hi $name")
//}

// Or Scala3:
def sayHi(name: String): Unit =
  val listN = List(1, 2, 3)
  val listS = List("toto", "tata")
  val listM: List[String | Int | Double] = List(3.0, 1, "hello")
  println(s"Hi $name")
