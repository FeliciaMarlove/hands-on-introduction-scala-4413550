@main def run: Unit = 
  val input: String = ???
  val output: String = ???
  etl(input, output)

def etl(inputFilePath: String, outputFilePath: String): Unit = 
  val extracted = extract(inputFilePath)
  val transformed = transform(extracted)
  // NO NEED TO WRITE "RETURN" IN SCALA!
  // last line of a method = return
  load(transformed, outputFilePath) 

def extract(input: String): List[String] = ???

def transform(data: List[String]): List[String] = ???

// Unit => "there's nothing to return but there's a side effect"
def load(data: List[String], output: String = "src/main/resources"): Unit = ???