package domain

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import model.UserCommentScore.UserContext
import play.api.libs.json.Json
import play.api.libs.ws.DefaultBodyReadables
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{Await, ExecutionContext, Future, duration}

object HttpClient {

  import DefaultBodyReadables._

  def request(
    user_id: Int
  )(implicit ec: ExecutionContext): Future[UserContext] = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val wsClient: StandaloneAhcWSClient = StandaloneAhcWSClient()

    wsClient
      .url(s"https://interview-external.moneywelfare.com/users/$user_id")
      .get()
      .map { response ⇒
        val body = response.body[String]

        Json.parse(body).as[UserContext]
      }
  }
}
