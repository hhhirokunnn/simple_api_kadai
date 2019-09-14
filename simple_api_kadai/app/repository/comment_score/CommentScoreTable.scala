package repository.comment_score

import scalikejdbc._

case class CommentScoreTable(id: Int,
                             comment_id: Int,
                             score_type: Int,
                             score: Float)

object CommentScoreTable extends SQLSyntaxSupport[CommentScoreTable] {
  override val useSnakeCaseColumnName = false
  override val connectionPoolName = Symbol("test")
  override val tableName = "comment_scores"

  val c: SyntaxProvider[CommentScoreTable] = CommentScoreTable.syntax("c")

  val * : WrappedResultSet => CommentScoreTable = set =>
    CommentScoreTable(
      id = set.int(c.resultName.id),
      comment_id = set.int(c.resultName.comment_id),
      score_type = set.int(c.resultName.score_type),
      score = set.float(c.resultName.score)
  )

}
