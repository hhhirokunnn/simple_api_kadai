package repository.user_comment

import scalikejdbc._

case class UserCommentTable(id: Int,
                            user_id: Int,
                            comment_date: String,
                            comment: String)

object UserCommentTable extends SQLSyntaxSupport[UserCommentTable] {
  override val useSnakeCaseColumnName = false
  override val connectionPoolName = Symbol("test")
  override val tableName = "user_comments"

  val uc: SyntaxProvider[UserCommentTable] = UserCommentTable.syntax("uc")

  val * : WrappedResultSet => UserCommentTable = set =>
    UserCommentTable(
      id = set.int(uc.resultName.id),
      user_id = set.int(uc.resultName.user_id),
      comment_date = set.string(uc.resultName.comment_date),
      comment = set.string(uc.resultName.comment)
  )
}
