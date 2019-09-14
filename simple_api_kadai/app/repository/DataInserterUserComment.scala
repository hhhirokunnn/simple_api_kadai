package repository

import scalikejdbc.NamedDB
import scalikejdbc.config.DBs
import scalikejdbc.interpolation.Implicits._

import scala.io.Source

object DataInserterUserComment {

  def main(args: Array[String]): Unit = {

    // 入力ファイル
    val source = Source.fromFile("csv/user_comment.csv")

    DBs.setupAll()

    source.getLines.foreach { line =>
      val Array(id, user_id, comment_date, comment) = line.split(",", 0)
      NamedDB(Symbol("test")) localTx { implicit session =>
        val res =
          sql"insert into user_comments values($id, $user_id, $comment_date, $comment);"
            .update()
            .apply
        if (res != 1) {
          println(s"id: $id")
        }
      }
    }
    DBs.closeAll()
  }
}
