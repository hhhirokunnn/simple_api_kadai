package repository

import scalikejdbc.NamedDB
import scalikejdbc.config.DBs
import scalikejdbc.interpolation.Implicits._

import scala.io.Source

object DateInserterCommentScore {

  def main(args: Array[String]): Unit = {

    // 入力ファイル
    val source = Source.fromFile("csv/comment_score.csv")
    DBs.setupAll()
    source.getLines.foreach { line =>
      val Array(comment_id, score_type, score) = line.split(",", 0)
      NamedDB(Symbol("test")) localTx { implicit session =>
        val res =
          sql"insert into comment_scores (comment_id, score_type, score)values($comment_id, $score_type, $score);"
            .update()
            .apply
        if (res != 1) {
          println(
            s"comment_id: $comment_id, score_type: $score_type, score: $score"
          )
        }
      }
    }
    DBs.closeAll()
  }
}
