package repository.comment_score

import repository.comment_score.CommentScoreTable.c
import scalikejdbc.{DBSession, NamedDB, withSQL}

class CommentScoreSelector(implicit session: DBSession) {

  def selectById(id: Int): Vector[CommentScoreTable] =
    withSQL {
      scalikejdbc.select.from(CommentScoreTable as c).where.eq(c.id, id)
    }.map(CommentScoreTable.*).collection.apply()
}
