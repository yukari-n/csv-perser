package dev.yukarin.service

import java.io.File
import com.github.tototoshi.csv.{CSVReader, CSVWriter, TSVFormat}
import scala.collection.mutable

trait MakeSetService {

  def makeAndCountSet(keys: Set[Int]): Unit = {
    println("指定のカラムの組でユニークにして出力")

    //implicit val format = new TSVFormat {}

    val filePath: String = ""
    val reader = CSVReader.open(new File(filePath))//(format)

    val output: String = ""
    val writer = CSVWriter.open(new File(output))//(format)

    val columnSet: mutable.Set[Seq[String]] = mutable.Set.empty
    var count = 0

    val it = reader.iterator
    while (it.hasNext) {
      count += 1
      val row = it.next()
      val key: Seq[String] = keys.map(key => row(key)).toSeq
      if (!columnSet.contains(key)) {
        columnSet += key
        writer.writeRow(key)
      }
    }
    reader.close()
    writer.close()

    println("出力しました")
  }

}
