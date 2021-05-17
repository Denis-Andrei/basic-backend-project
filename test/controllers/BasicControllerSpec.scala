package controllers


import models.Vehicle
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito.when
import org.scalatest.Matchers._
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status
import play.api.mvc.{ControllerComponents}
import play.api.test.{FakeRequest, Injecting}
import repositories.DataRepository
import play.api.test.Helpers._
import scala.concurrent.{ExecutionContext}


class BasicControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf[ControllerComponents]
  implicit lazy val executionContext: ExecutionContext = app.injector.instanceOf[ExecutionContext]
  val mockDataRepository: DataRepository = mock[DataRepository]


  object testController extends BasicController(
    controllerComponents,
    mockDataRepository,
    executionContext
  )
  val dataModel: Vehicle = Vehicle(
    3,
    true,
    "BMW"
  )
//  def status(of: Action[AnyContent]): Int = of.header.status
  "BasicController .getOneVehicle" should {
    "return Ok" when {
      "expected vehicle name submitted" in {
        when(mockDataRepository.getVehicle(any[String]))
          .thenReturn(Some(dataModel))

        val result = testController.getOneVehicleName("BMW")(FakeRequest())
        status(result) mustBe (Status.OK)
      }
    }
  }
  "return NotFound" when {

    "unexpected vehicle name submitted" in {
      when(mockDataRepository.getVehicle(any[String]))
        .thenReturn(None)

      val result = testController.getOneVehicleName("0")(FakeRequest())
      status(result) mustBe Status.NOT_FOUND
    }

  }
}