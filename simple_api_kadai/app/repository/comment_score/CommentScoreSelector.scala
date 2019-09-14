package repository.comment_score

import scalikejdbc.NamedDB
import CommentScoreTable.c
import scalikejdbc.withSQL

class CommentScoreSelector {

  def selectAll(): Vector[CommentScoreTable] = {
    NamedDB(Symbol("test")) localTx { implicit session =>
      withSQL {
        scalikejdbc.select.from(CommentScoreTable as c)
      }.map(CommentScoreTable.*).collection.apply()
    }
  }
}
