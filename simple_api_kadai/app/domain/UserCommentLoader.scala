package domain

import model.Comment
import repository.user_comment.{UserCommentSelector, UserCommentTable}
import scalikejdbc.DBSession

class UserCommentLoader(implicit session: DBSession) {

  def loadById(id: Int): Either[Exception, Comment] = {
    new UserCommentSelector().selectById(id) match {
      case Some(value) => Right(toCommentResponse(value))
      case None        => Left(new Exception("データない"))
    }
  }

  private def toCommentResponse(userCommentTable: UserCommentTable): Comment = {
    val Array(day: String, month: String, year: String) =
      userCommentTable.comment_date.split("/", 0)
    val rule = "%02d"
    val formattedDate = year + "-" + rule.format(month.toInt) + "-" + rule
      .format(day.toInt)

    Comment(
      id = userCommentTable.id,
      user_id = userCommentTable.user_id,
      date = formattedDate,
      text = userCommentTable.comment
    )
  }
}
