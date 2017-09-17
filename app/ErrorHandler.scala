import javax.inject.{Inject, Provider, Singleton}

import play.api._
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc._
import play.api.routing.Router

import scala.concurrent.Future

@Singleton
class ErrorHandler @Inject()(environment: Environment,
                             configuration: Configuration,
                             sourceMapper: OptionalSourceMapper,
                             router: Provider[Router])
  extends DefaultHttpErrorHandler(environment, configuration, sourceMapper, router)
          with Results {
  val logger : Logger = Logger(this.getClass)
  override protected def onProdServerError(request: RequestHeader,
                                           exception: UsefulException): Future[Result] = {
    Future.successful(
      InternalServerError("internal server error " + exception.getMessage)
    )
  }

  override protected def onDevServerError(request: RequestHeader,
                                          exception: UsefulException): Future[Result] = {
    Logger.error(" on dev server error")
    onProdServerError(request,exception)
  }
}
