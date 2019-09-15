package model

import play.api.libs.json.{JsPath, Writes}

case class DataResponse[A](data: A)

object DataResponse {

  implicit def resultsWrites[A: Writes]: Writes[DataResponse[A]] = {
    (JsPath \ "data").write[A].contramap(_.data)
  }
}
