package workshop.p6.either.example

import java.io

import cats.implicits._
import workshop.hello.HeaderSupport

import scala.util.Try

object EitherExample extends App with HeaderSupport {

  val r = for {
    x <- Either.right(1)
    y <- Either.right(2)
  } yield {
    x + y
  }
  println(r)

  val r2 = for {
    x <- { println("1") ; Either.right(1)}
    s <-                  Either.left("STOP !")
    y <- { println("2") ; Either.right(2)}
  } yield {
    x + y
  }
  println(r2)

  header("Transforming")

  val opt: Option[Int] = None
  val tried: Try[Int] = Try{throw new Exception ;2 }
  val eitherFromOpt: Either[String, Int] = Either.fromOption(opt, "EmptyError")
  val eitherFromTry: Either[Throwable, Int] = Either.fromTry(tried)

  val result: Either[io.Serializable, Int] = for {
    x <- eitherFromOpt
    y <- eitherFromTry
  } yield {
    x + y
  }
  println(result)

  val result2: Either[String, Int] = for {
    x <- eitherFromOpt
    errorString = "Error:"
    y <- eitherFromTry.leftMap(exception => s"$errorString $exception")
  } yield {
    x + y
  }
  println(result2)

}
