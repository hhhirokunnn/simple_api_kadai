package domain

import model.UserCommentScore.{CommentScore, UserContext}
import model.{Comment, UserCommentScore}
import repository.comment_score.{CommentScoreSelector, CommentScoreTable}
import scalikejdbc.DBSession

import scala.concurrent.Await
import scala.concurrent.duration._

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

    import scala.concurrent.ExecutionContext.Implicits.global

    val requestUser =
      Await.result(HttpClient.request(userComment.user_id), 1.minute)

    UserCommentScore(
      id = userComment.id,
      user = UserContext(id = requestUser.id, name = requestUser.name),
      date = userComment.date,
      text = userComment.text,
      comment_scores = commentScores.map { s =>
        val bd = BigDecimal(s.score)
        CommentScore(
          id = s.id,
          comment_id = s.comment_id,
          `type` = s.score_type,
          value = bd.setScale(2, scala.math.BigDecimal.RoundingMode.FLOOR)
        )
      }
    )
  }
}
