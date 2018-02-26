package workshop.p5.tried

import org.scalatest.{FlatSpec, Matchers}
import workshop.p5.tried.example.TryExamples


class TrySpec extends FlatSpec with Matchers {

  "Retry" should "work" in {

    val result =
      Retry.retry(times = 10) {
        TryExamples.evenOrError()
      }

    result.isSuccess shouldEqual true
  }

}