package repository.user_comment

import UserCommentTable.uc
import scalikejdbc.{DBSession, NamedDB, withSQL}

class UserCommentSelector(implicit session: DBSession) {

  def selectAll(): Vector[UserCommentTable] =
    withSQL {
      scalikejdbc.select.from(UserCommentTable as uc)
    }.map(UserCommentTable.*).collection.apply()

  def selectById(id: Int): Option[UserCommentTable] =
    withSQL {
      scalikejdbc.select.from(UserCommentTable as uc).where.eq(uc.id, id)
    }.map(UserCommentTable.*).first.apply()
}
