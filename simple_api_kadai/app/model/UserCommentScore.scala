package model

import model.UserCommentScore.{CommentScore, UserContext}
import play.api.libs.json.{Json, Writes}

/**
  * {
  * "data": {
  * "id": 1,
  * "user": {
  * "id": 2,
  * "name": "ユーザー2"
  * },
  * "date": "2015-01-01", ※2
  * "text": "左手には少しさがって博物の教室がある。\r",
  * "comment_scores": [
  * {"id": 1, "comment_id": 1, "type": 0, "value": 0.23},
  * {"id": 2, "comment_id": 1, "type": 1, "value": 0.8}
  * ]
  * }
  * }
  */
case class UserCommentScore(id: Int,
                            user: UserContext,
                            date: String,
                            text: String,
                            comment_scores: Seq[CommentScore])

object UserCommentScore {

  case class UserContext(id: Int, name: String)
  object UserContext {
    implicit val writes: Writes[UserContext] = Json.writes[UserContext]
  }

  case class CommentScore(id: Int, comment_id: Int, `type`: Int, value: Float)
  object CommentScore {
    implicit val writes: Writes[CommentScore] = Json.writes[CommentScore]
  }

  implicit val writes: Writes[UserCommentScore] = Json.writes[UserCommentScore]
}
