package repository.user_comment

import UserCommentTable.uc
import scalikejdbc.{NamedDB, withSQL}

class UserCommentSelector {

  def selectAll(): Vector[UserCommentTable] = {
    NamedDB(Symbol("test")) localTx { implicit session =>
      withSQL {
        scalikejdbc.select.from(UserCommentTable as uc)
      }.map(UserCommentTable.*).collection.apply()
    }
  }
}
