package workshop.p5.tried

import scala.annotation.tailrec
import scala.util.Try

private object Solution {

  private object Retry extends Retry {

    def retry[T](times: Int)(body: => T): Try[T] = {
      assert(times > 0)

      @tailrec
      def loop(n: Int, lastResult: Try[T]): Try[T] = {
        n match {
          case 0 =>
            lastResult
          case n =>
            val result = Try {
              body
            }
            loop(n - 1, result)
        }
      }
      loop(times, Try {
        body
      })

    }

  }
}
