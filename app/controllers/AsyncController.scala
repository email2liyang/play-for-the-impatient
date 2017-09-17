package controllers

import javax.inject.Inject

import akka.stream.scaladsl.Source
import play.api.Configuration
import play.api.libs.concurrent.Futures
import play.api.libs.concurrent.Futures._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class AsyncController @Inject()(implicit ec: ExecutionContext,
                                futures: Futures,
                                config: Configuration,
                                cc: ControllerComponents
                               ) extends AbstractController(cc) {

  def asyncPi() = Action.async { request: Request[AnyContent] =>
    val futureInt = Future(Math.PI.toInt)
    futureInt
      .withTimeout(1.second)
      .map(pi => Ok("pi is " + pi))
      .recover {
        case e: scala.concurrent.TimeoutException =>
          InternalServerError("timeout")
      }
  }

  def chunkedSource() = Action {
    val source = Source.apply(List("a", "b", "c"))
    Ok.chunked(source)
  }

}
