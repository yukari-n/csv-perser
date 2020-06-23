package dev.yukarin.service

import java.io.File
import scala.collection.mutable

import com.github.tototoshi.csv.{CSVReader, CSVWriter, TSVFormat}
import dev.yukarin.Main.{outputFilePath, sourceFilePath}

trait CountService {
  def count(): Unit = {
    println("CSVのレコード件数を数える")

    val reader = CSVReader.open(new File(sourceFilePath))
    val it = reader.iterator
    var count = 0
    while (it.hasNext) {
      count += 1
      it.next()
    }
    reader.close()

    println("レコード数: " + count + "件")
  }

  def countByID(idColumn: Int): Unit = {
    println("CSVのレコード件数をIDごとに数える")
    implicit val format = new TSVFormat {}

    val reader = CSVReader.open(new File(sourceFilePath))(format)
    val it = reader.iterator
    val countMap: mutable.Map[String,Int] = mutable.Map.empty

    while (it.hasNext) {
      val row = it.next()
      val id = row(idColumn)
      if (countMap.contains(id)) {countMap(id) = countMap.apply(id) + 1}
      else {countMap += (id -> 1)}
    }
    reader.close()

    val writer = CSVWriter.open(new File(outputFilePath))(format)
    writer.writeRow(Seq("ID", "count"))
    countMap.map { kv =>
      writer.writeRow(Seq(kv._1, kv._2))
    }
    writer.close()

    println("数えた")
  }
}
