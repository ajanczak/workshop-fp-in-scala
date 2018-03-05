package workshop.p5.tried.example

import workshop.hello.HeaderSupport

import scala.util.{Random, Try}
import cats.implicits._

object TryExamples extends App with HeaderSupport {

  final def evenOrError(): Int = {
    val number = new Random().nextInt(100)
    number % 2 match {
      case 0 => number
      case _ => throw new Exception(s"Not even number: $number")
    }
  }

  val results = 1 to 10 map { _ =>
    Try {
      evenOrError
    }
  }
  println(results)

  val r1: Try[Int] = for {
    x <- Try(1)
    y <- Try(2)
  } yield {
    x + y
  }


  // combine - works "kind of like" flatmap
  val r2: Try[Int] = Try(1) combine Try(2)

  assert(r1 == r2)
  println(r1, r2)


  val r = results.reduce( _.combine(_))
  println(r)

}
