package myapp.backend

import com.twitter.finagle.http.{ Request, Response }
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.logging.filter.{ LoggingMDCFilter, TraceIdMDCFilter }
import com.twitter.finatra.logging.modules.Slf4jBridgeModule
import myapp.backend.http.StaticFileController
import com.twitter.finatra.http.modules.DocRootModule

object MyAppServer extends HttpServer { //} with ThriftServer {

  override def modules = Seq(
    Slf4jBridgeModule,
    DocRootModule
  )

  override def configureHttp(router: HttpRouter) {
    import com.twitter.finatra.http.filters._
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[StaticFileController]
  }

  //  override def configureThrift(router: ThriftRouter) {
  //    import com.twitter.finatra.thrift.filters._
  //    router
  //      .filter[LoggingMDCFilter[ThriftRequest, Any]]
  //      .filter[TraceIdMDCFilter[ThriftRequest, Any]]
  //      .filter[ThriftMDCFilter]
  //      .filter[AccessLoggingFilter]
  //      .filter[StatsFilter]
  //      .filter[ExceptionTranslationFilter]
  //      .filter[ClientIdWhitelistFilter]
  //    //.add[CalculatorImpl](FilteredCalculator.create)
  //  }
}
