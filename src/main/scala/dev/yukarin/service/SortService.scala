package dev.yukarin.service

import java.io.File
import com.github.tototoshi.csv.CSVWriter
import dev.yukarin.entity.Order

trait SortService {
  def sortBy(colmn: Int, order: Order, data: Seq[Seq[String]]): Unit = {
    println("ソート開始")
    val sorted: Seq[Seq[String]] = order match {
      case Order.ASC => data.sortBy(_(colmn))
      case Order.DESC => data.sortBy(_(colmn))(Ordering[String].reverse)
    }
    val filePath = ""
    val writer = CSVWriter.open(new File(filePath))
    writer.writeAll(sorted)
    writer.close()
    println("ソート完了。出力ファイル: " + filePath)
  }
}
