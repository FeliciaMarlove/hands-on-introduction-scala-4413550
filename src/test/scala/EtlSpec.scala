import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import scala.util.Try
import scala.util.Using
import scala.io.Source
import scala.util.Success
// AnyFreeSpec is one of the testing styles available
// should.Matchers => replace assert by should

class EtlSpec extends AnyFreeSpec with Matchers {
  "etl" - {
    "transforms a text file by making all the text lowercase and saving this to a new file" in {
      val input = "/workspaces/hands-on-introduction-scala-4413550/src/test/scala/resources/input.txt"
      val output = "/workspaces/hands-on-introduction-scala-4413550/src/test/scala/resources/output.txt"
      val expectedFileContents = List("hello world")
      etl(input, output)
      // wrapped in Success because we return a Try
      readFile(output) shouldEqual Success(expectedFileContents)
    }
  }
  private def readFile(filePath: String): Try[List[String]] = 
    Using(Source.fromFile(filePath))(_.getLines.toList)
    //Source => scala io, representation of a File => buffered (iterable) ==> access to getLines
    //getLines => collection of lines ==> toList to cast specifically into a list
    // Using = automatic resource management (ex makes sure we close the resource)
    // Return a Try to handle errors!
}
