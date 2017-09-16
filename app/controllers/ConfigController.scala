package controllers

import javax.inject.{Inject, Singleton}

import play.api.Configuration
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class ConfigController @Inject()(config: Configuration, cc: ControllerComponents) extends AbstractController(cc) {

  def getConfig() = Action { implicit request: Request[AnyContent] =>
    val url = config.get[String]("db.url")
    val passWord = config.getOptional[String]("db.password").getOrElse("None")
    Ok(views.html.config(url, passWord, config))
  }
}