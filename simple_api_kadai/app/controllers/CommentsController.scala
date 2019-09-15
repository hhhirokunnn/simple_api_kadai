package controllers

import domain.{UserCommentLoader, UserCommentScoreLoader}
import javax.inject._
import model.{Comment, DataResponse, UserCommentScore}
import play.api.libs.json.Json
import play.api.mvc._
import scalikejdbc.NamedDB
import scalikejdbc.config.DBs

@Singleton
class CommentsController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  def showUserComment(id: Int) = Action {
    implicit request: Request[AnyContent] =>
      DBs.setupAll()
      NamedDB(Symbol("test")) localTx { implicit session =>
        new UserCommentLoader().loadById(id)
      } match {
        case Right(v) =>
          DBs.closeAll()
          Ok(Json.toJson(DataResponse[Comment](v)))
        case Left(e) =>
          DBs.closeAll()
          BadRequest(e.getMessage)
      }
  }

  def showUserCommentScore(id: Int) = Action {
    implicit request: Request[AnyContent] =>
      DBs.setupAll()
      NamedDB(Symbol("test")) localTx { implicit session =>
        new UserCommentScoreLoader().loadById(id)
      } match {
        case Right(v) =>
          DBs.closeAll()
          Ok(Json.toJson(DataResponse[UserCommentScore](v)))
        case Left(e) =>
          DBs.closeAll()
          BadRequest(e.getMessage)
      }
  }
}
