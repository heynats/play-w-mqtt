package controllers

import javax.inject.{Singleton, Inject}

import models.{DB, TSData}
import models.TSData._

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}


@Singleton
class Application @Inject() (webJarAssets: WebJarAssets, db: DB) extends Controller {

  def index = Action {
    Ok(views.html.index(webJarAssets, "Application is ready."))
  }

  def dataSet = Action {
    val dataset = db.query[TSData].fetch()
    Ok(Json.toJson(dataset))
  }

  def addData = Action(parse.json) { request =>
    val data = db.save(request.body.as[TSData])
    Ok(Json.toJson(data))
  }

}