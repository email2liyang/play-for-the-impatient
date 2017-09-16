package controllers

import javax.inject.{Inject, Singleton}

import play.api.Configuration
import play.api.mvc._

@Singleton
class ConfigController @Inject()(config: Configuration, cc: ControllerComponents) extends AbstractController(cc) {

  def getConfig() = Action { implicit request: Request[AnyContent] =>
    val url = config.get[String]("db.url")
    val passWord = config.getOptional[String]("db.password").getOrElse("None")
    Ok(views.html.config(url, passWord, config))
  }

  def echo() = Action {
    Ok("hello world")
  }

  def notFound() = Action {
    NotFound("not found")
  }

  def hello(to: String) = Action {
    Ok(s"hello $to").as(HTML).withCookies(Cookie("k1", "v1"))
  }

  def redirect() = Action {
    Redirect(routes.ConfigController.hello("bob"))
  }

  // use curl -v -X POST localhost:9000/json -d '{"k1":"v1"}' -H "Content-Type: application/json to test
  def json() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody = body.asJson

    jsonBody.map { json =>
      Ok("get :" + json.toString())
    }.getOrElse(
      BadRequest("invalid json format")
    )
  }
}
