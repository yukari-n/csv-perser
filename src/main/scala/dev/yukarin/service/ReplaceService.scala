package dev.yukarin.service

import java.io.File
import com.github.tototoshi.csv.{CSVReader, CSVWriter}
import dev.yukarin.Main.{outputFilePath, sourceFilePath}

trait ReplaceService {
  def replaceAndMakeUnique(colmn: Int): Unit = {
    println("指定のカラムの値を置き換えて一意にする")

    val reader = CSVReader.open(new File(sourceFilePath))
    val writer = CSVWriter.open(new File(outputFilePath))

    val it = reader.iterator
    var count = 0
    while (it.hasNext) {
      count += 1
      val row = it.next()
      val newRow = row patch (colmn, Seq(row(colmn) + count.toString), 1)
      writer.writeRow(newRow)
    }
    reader.close()
    writer.close()

    println("レコード数: " + count + "件を置き換えました")
  }
}
