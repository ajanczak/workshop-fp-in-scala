package workshop.p5.tried

import scala.util.Try

trait Retry {
  def retry[T](times: Int)(body: => T): Try[T]
}

object Retry extends Retry {
  override def retry[T](times: Int)(body: => T): Try[T] = ??? // Luke, use the force of tailrec !
}
