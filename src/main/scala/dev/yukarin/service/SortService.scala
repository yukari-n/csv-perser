package dev.yukarin.service

import java.io.File
import com.github.tototoshi.csv.CSVWriter
import dev.yukarin.entity.Order
import dev.yukarin.Main.outputFilePath

trait SortService {
  def sortBy(colmn: Int, order: Order, data: Seq[Seq[String]]): Unit = {
    println("ソート開始")
    val sorted: Seq[Seq[String]] = order match {
      case Order.ASC => data.sortBy(_(colmn))
      case Order.DESC => data.sortBy(_(colmn))(Ordering[String].reverse)
    }
    val writer = CSVWriter.open(new File(outputFilePath))
    writer.writeAll(sorted)
    writer.close()
    println("ソート完了。出力ファイル: " + outputFilePath)
  }
}
