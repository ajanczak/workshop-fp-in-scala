package workshop.p3.laziness.solution

import workshop.p3.laziness.TimeLogger


object Solution {

  private trait Timed {
    /**
      * Logs time in milliseconds
      * @param body code to be measured
      * @param logger TimeLogger
      * @tparam T type of body
      * @return body execution result
      */
    def timed[T](logger: TimeLogger)(body: => T): T
  }

  private object Timer extends Timed {
    override def timed[T](logger: TimeLogger)(body: => T): T = {
      val t0 = System.currentTimeMillis()
      val resultOfBodyExec = body
      val t1 = System.currentTimeMillis()
      val elapsedTime = t1 - t0
      logger.log(elapsedTime) // side effect, well yes, but not crucial one
      resultOfBodyExec
    }
  }
}