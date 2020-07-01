package dev.yukarin.service

import java.io.File
import com.github.tototoshi.csv.{CSVReader, CSVWriter, TSVFormat}
import dev.yukarin.Main.{outputFilePath, sourceFilePath}

trait SeparateService {
  implicit val format = new TSVFormat {}

  def extract(conditions: Map[Int, String]): Unit = {
    println("指定のカラムの値が条件に一致する場合書き出す")
    println("条件が空文字の場合は条件なしカラム（単に書き出したいカラム）として扱う")

    val reader = CSVReader.open(new File(sourceFilePath))(format)
    val writer = CSVWriter.open(new File(outputFilePath))(format)

    val it = reader.iterator
    while (it.hasNext) {
      val row = it.next()
      var matched = true
      conditions.map { kv =>
        if (kv._2 != "" && row(kv._1) != kv._2) { matched = false }
      }
      if (matched) {
        val newRow: Seq[String] = conditions.keys.map(key => row(key)).toSeq
        writer.writeRow(newRow)
      }
    }
    reader.close()
    writer.close()

    println(s"出力しました: ${outputFilePath}")
  }

  def pickup(column: Int, searchText: String): Unit = {
    println("指定のカラムの値が条件に一致する場合、その行全てを書き出す")

    val reader = CSVReader.open(new File(sourceFilePath))(format)
    val writer = CSVWriter.open(new File(outputFilePath))(format)

    val it = reader.iterator
    while (it.hasNext) {
      val row = it.next()
      if (row(column) == searchText) { writer.writeRow(row) }
    }

    reader.close()
    writer.close()

    println(s"出力しました: ${outputFilePath}")
  }
}
