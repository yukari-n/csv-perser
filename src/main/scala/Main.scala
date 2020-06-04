import com.github.tototoshi.csv.{CSVFormat, CSVReader, DefaultCSVFormat}
import java.io.File

// https://github.com/tototoshi/scala-csv

object Main {
  def main(args: Array[String]): Unit = {
    println("CSVのレコード件数を数える")

    val filePath: String = ""

    val reader = CSVReader.open(new File(filePath))
    val it = reader.iterator
    var count = 0
    while (it.hasNext) {
      count += 1
      it.next()
    }
    reader.close()

    println("レコード数: " + count + "件")
  }
}