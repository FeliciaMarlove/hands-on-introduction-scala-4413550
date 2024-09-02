import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.{Using, Try}
import Etl.*

@main def run: Unit =
  val input: String = "src/main/resources/input.txt"
  val output: String = "src/main/resources/output.txt"
  etl(input, output)(using Etl.IntImpl)
  