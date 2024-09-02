object Etl:

  enum EtlError:
    case ExtractError, LoadError
    // Either = data structure for 2 possible values
    // often used for success vs error values
    // => map response in Right or Left

  sealed trait Etl[A, B]:
    def extract(input: String): Either[EtlError, A]
    def transform(data: A): Either[EtlError, B]
    def load(data: B, output: String): Either[EtlError, Unit]

  given StringImpl: Etl[List[String], List[String]] with
    def extract(input: String): Either[EtlError, List[String]] = FileUtils.extract(input)
    def transform(data: List[String]): Either[EtlError, List[String]] = Right(data.map(_.toLowerCase)).withLeft[EtlError]
    def load(data: List[String], output: String): Either[EtlError, Unit] =
      FileUtils.load(data, output)

  given IntImpl: Etl[List[String], List[Int]] with
    def extract(input: String): Either[EtlError, List[String]] = FileUtils.extract(input)
    def transform(data: List[String]): Either[EtlError, List[Int]] = Right(data.map(_.toInt).map(_ * 2)).withLeft[EtlError]
    def load(data: List[Int], output: String): Either[EtlError, Unit] =
      FileUtils.load(data, output)

  def etl[A, B](inputFilePath: String, outputFilePath: String)(using
      etl: Etl[A, B]
  ): Either[EtlError, Unit] =
    // For Comprehensions https://docs.scala-lang.org/tour/for-comprehensions.html
    // sequence of operations
    for 
      extracted <- etl.extract(inputFilePath)
      transformed <- etl.transform(extracted)
      _ <- etl.load(transformed, outputFilePath)
    yield()
