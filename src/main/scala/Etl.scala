object Etl:
  // a trait is similar to a Java 8+ interface (contract)
  // sealed = can only be extended in the same file as its decoration
  // using Generics => allow different implementations
  sealed trait Etl[A, B]:
    def extract(input: String): A
    def transform(data: A): B
    def load(data: B, output: String): Unit

    // "typed" implementations of the trait
    // Scala3 ! (see doc for similar implementation in S2)
  given StringImpl: Etl[List[String], List[String]] with
    def extract(input: String): List[String] = FileUtils.extract(input)
    def transform(data: List[String]): List[String] = data.map(_.toLowerCase)
    def load(data: List[String], output: String): Unit = FileUtils.load(data, output)

  given IntImpl: Etl[List[String], List[Int]] with
    def extract(input: String): List[String] = FileUtils.extract(input)
    def transform(data: List[String]): List[Int] = data.map(_.toInt).map(_ * 2)
    def load(data: List[Int], output: String): Unit = FileUtils.load(data, output)

  def etl[A, B](inputFilePath: String, outputFilePath: String)(using etl: Etl[A, B]): Unit =
    val extracted = etl.extract(inputFilePath)
    val transformed = etl.transform(extracted)
    etl.load(transformed, outputFilePath)