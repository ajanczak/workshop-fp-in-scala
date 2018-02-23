package workshop.example

import org.scalatest._
import workshop.hello.Hello

class HelloSpec extends FlatSpec with Matchers {
  "The Hello object" should "say workshop.hello" in {
    Hello.greeting shouldEqual "hello world"
  }

  it should ("1 = 1") in {
    1 shouldEqual 1
  }
}
