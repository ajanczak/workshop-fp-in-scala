package workshop.p3.laziness

trait Timed {

  /**
  * Measure time of given code
  * @param logger TimeLogger
  * @param body code to measure
  * @tparam T Type of what the body will return
  * @return Result of executing body
  */
  def timed[T](logger: TimeLogger)(body: => T): T
}

object Timer extends Timed {
  override def timed[T](logger: TimeLogger)(body: => T): T = ???
}