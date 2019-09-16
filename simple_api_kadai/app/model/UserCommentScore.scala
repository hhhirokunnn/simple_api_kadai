package model

import model.UserCommentScore.{CommentScore, UserContext}
import play.api.libs.json.{Format, Json, Writes}

case class UserCommentScore(id: Int,
                            user: UserContext,
                            date: String,
                            text: String,
                            comment_scores: Seq[CommentScore])

object UserCommentScore {

  case class UserContext(id: Int, name: String)
  object UserContext {
    implicit val format: Format[UserContext] = Json.format[UserContext]
  }

  case class CommentScore(id: Int,
                          comment_id: Int,
                          `type`: Int,
                          value: BigDecimal)
  object CommentScore {
    implicit val writes: Writes[CommentScore] = Json.writes[CommentScore]
  }

  implicit val writes: Writes[UserCommentScore] = Json.writes[UserCommentScore]
}
