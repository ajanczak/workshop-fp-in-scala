package workshop.p3.laziness

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class TimerSpec extends FlatSpec with Matchers {

  "Timer" should "return a correct value and log" in {

    val testLogger: TestLogger = new TestLogger()
    val max = 9999

    val r =
      Timer.timed(testLogger) {
        (1 to max)
          .map(_ * 2)
          .map(_ * 3)
          .map(_ * 4)
          .max
      }

    r shouldEqual max * 2 * 3 * 4

    testLogger.log.head should (be > (1L) and be <= (100L))
  }

  it should "work with nested Timer correctly" in {

    val testLogger: TestLogger = new TestLogger()
    val inside = "This is madness"

    val r =
      Timer.timed(testLogger) {
        Thread.sleep(1000)
        Timer.timed(testLogger) {
          Thread.sleep(1000)
          inside
        }
      }

    r shouldEqual inside

    testLogger.log.head shouldBe 1000L+-100L
    testLogger.log(1)   shouldBe 2000L+-100L
  }
}


class TestLogger extends TimeLogger {

  val log = mutable.ListBuffer[Long]()

  override def log(value: Long): Unit = {
    log += value
  }
}