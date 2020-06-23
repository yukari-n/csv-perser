package dev.yukarin

import java.io.File

import com.github.tototoshi.csv.CSVReader
import dev.yukarin.entity.Order
import dev.yukarin.service._

object Main {

  //https://github.com/tototoshi/scala-csv

  def countService: CountService = new CountService {}
  def makeSetService: MakeSetService = new MakeSetService {}
  def replaceService: ReplaceService = new ReplaceService {}
  def sortService: SortService = new SortService {}

  val sourceFilePath: String = ""
  val outputFilePath: String = ""

  def main(args: Array[String]): Unit = {
    countService.countByID(5)
    //makeSetService.makeAndCountSet(Set(2))
    //replaceService.replaceAndMakeUnique(32)
    //sortService.sortBy(9, Order.DESC, readAllLine())
  }

  def readAllLine(): Seq[Seq[String]] = {
    println("全行読み込み開始")
    val reader = CSVReader.open(new File(sourceFilePath))
    val data: Seq[Seq[String]] = reader.all()
    reader.close()
    println("全行読み込み完了")
    data
  }
}
