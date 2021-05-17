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

    //    dataRepository.getVehicle(vehicleName) map { vehicle =>
    //      // If the vehicle was found, return a 200 with the post data as JSON
    //      Ok(Json.toJson(vehicle))
    //    } getOrElse NotFound // otherwise, return Not Found

    val vehicle = dataRepository.getVehicle(vehicleName)
     vehicle match {
      case Some(Vehicle(wheels,heavy,name)) => Ok(Json.toJson(vehicle.get))
      case _ =>  NotFound
    }

  }
}