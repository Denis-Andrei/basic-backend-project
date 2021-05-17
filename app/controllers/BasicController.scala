package controllers
import models.Vehicle
import play.api.libs.json.Json

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, AnyContent, BaseController, ControllerComponents, Request}
import repositories.DataRepository

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class BasicController @Inject()(cc: ControllerComponents,
                                dataRepository: DataRepository, ec: ExecutionContext)
  extends AbstractController(cc) {


  def index = Action { implicit request =>
    Ok("Hello, Scala!")
  }

  def getOneVehicleName(vehicleName: String) = Action { implicit request: Request[AnyContent] =>

    val vehicle = dataRepository.getVehicle(vehicleName)
     vehicle match {
      case Some(Vehicle(wheels,heavy,name)) => Ok(Json.toJson(vehicle.get))
      case _ =>  NotFound
    }

  }
}