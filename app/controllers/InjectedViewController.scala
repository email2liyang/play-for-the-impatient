package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, ControllerComponents}

class InjectedViewController @Inject()(sumarise: views.html.summarise,
                                       cc: ControllerComponents)
  extends AbstractController(cc) {

  def summarise() = Action {
    Ok(sumarise("items"))
  }
}
