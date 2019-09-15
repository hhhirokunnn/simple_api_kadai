package domain

import model.UserCommentScore.CommentScore
import model.{Comment, UserCommentScore}
import repository.comment_score.{CommentScoreSelector, CommentScoreTable}
import scalikejdbc.DBSession

class UserCommentScoreLoader(implicit session: DBSession) {

  def loadById(comment_id: Int): Either[Exception, UserCommentScore] = {
    val userComment = new UserCommentLoader().loadById(comment_id)
    val commentScores = new CommentScoreSelector().selectById(comment_id)
    userComment match {
      case Right(v) => Right(toUserCommentScore(v, commentScores))
      case _        => Left(new Exception("UserCommentScoreの作成に失敗"))
    }
  }

  private def toUserCommentScore(
    userComment: Comment,
    commentScores: Vector[CommentScoreTable]
  ): UserCommentScore = {

    UserCommentScore(
      id = userComment.id,
      user =
        UserCommentScore.UserContext(id = userComment.user_id, name = "test"),
      date = userComment.date,
      text = userComment.text,
      comment_scores = commentScores.map { s =>
        CommentScore(
          id = s.id,
          comment_id = s.comment_id,
          `type` = s.score_type,
          value = s.score
        )
      }
    )
  }
}
