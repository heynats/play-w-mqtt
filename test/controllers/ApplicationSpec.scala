package controllers

import play.api.inject.guice.GuiceApplicationBuilder
import scala.reflect.ClassTag

import models.TSData
import org.specs2.mutable._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

trait Inject {
  lazy val injector = (new GuiceApplicationBuilder).injector()

  def inject[T : ClassTag]: T = injector.instanceOf[T]
}

class ApplicationSpec extends Specification with Inject {
  lazy val appController = inject[Application]

  "Application" should {

    "add a data point" in  {
      val addData = appController.addData()(FakeRequest(POST, "/tsdata").withBody(
        Json.parse("""{"station": "MACHINE1", "status": false, "temp": 28.1, "humid": 67.0}"""))
        )
      status(addData) must equalTo(OK)
      Json.parse(contentAsString(addData)).as[TSData].station must beEqualTo ("MACHINE1")
      Json.parse(contentAsString(addData)).as[TSData].status must beEqualTo (false)
      Json.parse(contentAsString(addData)).as[TSData].temp must beEqualTo (28.1)
      Json.parse(contentAsString(addData)).as[TSData].humid must beEqualTo (67.0)
    }

    "get all data" in {
      val dataset = appController.dataSet()(FakeRequest(GET, "/tsdata"))
      status(dataset) must equalTo(OK)
      Json.parse(contentAsString(dataset)).as[Seq[TSData]].length must beGreaterThan (0)
    }

  }
}