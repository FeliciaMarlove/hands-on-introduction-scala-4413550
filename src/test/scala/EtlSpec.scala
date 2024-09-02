import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import scala.util.Using
import scala.io.Source
import scala.util.{Try, Success}

class EtlSpec extends AnyFreeSpec with Matchers {

  "etl" - {
    "transforms a text file by making all the text lowercase and saves it to a new file" in {
      val input =
        "src/test/resources/testInput.txt"
      val output =
        "src/test/resources/testOutput.txt"
      val expectedFileContents = List("hello world")

      etl(input, output)(using Etl.StringImpl)

      readFile(output) shouldEqual Success(expectedFileContents)
    }
        "transforms a text file by doubling all integers and saves it to a new file" in {
      val input =
        "src/test/resources/testInput2.txt"
      val output =
        "src/test/resources/testOutput2.txt"
      val expectedFileContents = List("0","10","12","12","16","14","18","18")

      etl(input, output)(using Etl.IntImpl)

      readFile(output) shouldEqual Success(expectedFileContents)
    }
  }

  private def readFile(filePath: String): Try[List[String]] =
    Using(Source.fromFile(filePath))(_.getLines.toList)
}
