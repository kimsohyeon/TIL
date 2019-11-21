package controllers

import java.lang.annotation.Annotation

import javax.inject._
import play.api._
import play.api.mvc._
import akka.stream.scaladsl._
import io.swagger.annotations._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
@Api("HomeController API")
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  @ApiOperation(value = "echo API", httpMethod = "POST", produces="plain/text", consumes = "plain/text")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Custom Warning 400"),
    new ApiResponse(code = 404, message = "Custom Warning 404")))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "client request text", value = "client request to response back", required = true, paramType = "body")))
    def echo = Action { implicit request: Request[AnyContent] =>
      request.body.asRaw.flatMap(_.asBytes())
        .map( x => Ok(x.decodeString("utf-8"))).getOrElse(NotFound)
    }

  @ApiOperation(value = "id", httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Pet not found")))
  def getId(@ApiParam(value = "ID fetch") id: String) =
    Action { implicit request: Request[AnyContent] =>
      Ok("Got ID [" + id + "]")
    }

}
