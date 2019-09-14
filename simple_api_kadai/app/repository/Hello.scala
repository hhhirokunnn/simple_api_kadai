package repository

import repository.user_comment.UserCommentSelector
import scalikejdbc.config.DBs

object Hello {

  def main(args: Array[String]): Unit = {

    DBs.setupAll()

    println(new UserCommentSelector().selectAll())

    DBs.closeAll()
  }
}
