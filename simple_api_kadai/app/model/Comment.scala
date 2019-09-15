package model

import play.api.libs.json.{Json, Writes}

case class Comment(id: Int, user_id: Int, date: String, text: String)

object Comment {

  implicit val writes: Writes[Comment] = Json.writes[Comment]
}
