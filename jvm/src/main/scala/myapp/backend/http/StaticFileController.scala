package myapp.backend.http

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

class StaticFileController extends Controller {

  get("/:*") { request: Request =>
    response.ok.fileOrIndex(request.params("*"), "index-dev.html")
  }
}
