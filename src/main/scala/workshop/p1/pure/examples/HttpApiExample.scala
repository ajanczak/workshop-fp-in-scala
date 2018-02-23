package workshop.p1.pure.examples


import scala.concurrent.Future

object HttpApiExample {

  // Just for workshop.example to look nice
  type Car = String
  type CarAddResult = String

  // Contains information about address, authorisation etc.
  type HttpContext = String

  // GET / POST
  type Request[T] = String
  type Response[T] = String

  // eager, have some http client inside
  // Car => Unit
  // testable only with HTTP mocking
  def saveCar(car: Car): Unit = ???

  // VS

  // Pure, testable
  // I can test validity of request, check e.g. json serialization
  def saveCarRequest(car: Car): Request[Car] = ???

  // Not pure, but testable
  // HttpContext easily replaceable for mock impl
  def invokeCarSave[T](req: Request[T])(hc: HttpContext): Future[Response[CarAddResult]] = {
      """
        |      ...
        |      hc.invoke(req)
        |      ...
      """.stripMargin
    ???
  }
}
