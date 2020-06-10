package dev.yukarin

import java.io.File

import com.github.tototoshi.csv.CSVReader
import dev.yukarin.entity.Order
import dev.yukarin.service._

object Main {

  //https://github.com/tototoshi/scala-csv

  def replaceService: ReplaceService = new ReplaceService {}
  def sortService: SortService = new SortService {}

  val filePath: String = ""

  def main(args: Array[String]): Unit = {
    replaceService.replaceAndMakeUnique(32)
    //sortService.sortBy(9, Order.DESC, readAllLine())
  }

  def readAllLine(): Seq[Seq[String]] = {
    println("全行読み込み開始")
    val reader = CSVReader.open(new File(filePath))
    val data: Seq[Seq[String]] = reader.all()
    reader.close()
    println("全行読み込み完了")
    data
  }

  def count(): Unit = {
    println("CSVのレコード件数を数える")

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
