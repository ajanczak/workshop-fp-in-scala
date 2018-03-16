package workshop.p6.either.example

import java.io

import workshop.hello.HeaderSupport

import scala.util.Try

object EitherExample extends App with HeaderSupport {

  // extends scala std Either
  import cats.implicits._

  // Successful Either operation
  val r = for {
    x <- Either.right(1)
    y <- Either.right(2)
  } yield {
    x + y
  }
  println(r)

  // Flat map stopped when spotted left side
  val r2 = for {
    x <- { println("evaluate x") ; Either.right(1) }
    s <- { println("evaluate s") ; Either.left("STOP !") }
    y <- { println("evaluate y") ; Either.right(2) }
  } yield {
    x + y
  }
  println(r2)

  header("Transforming Option/Try -> Either")

  val opt: Option[Int] = None
  val tried: Try[Int] = Try{ throw new Exception ;2 }

  // create Either from Option
  val eitherFromOpt: Either[String, Int] = Either.fromOption(opt, "EmptyError")

  // create Either from Try
  val eitherFromTry: Either[Throwable, Int] = Either.fromTry(tried)

  val result: Either[io.Serializable, Int] = for {
    x <- eitherFromOpt
    y <- eitherFromTry
  } yield {
    x + y
  }
  println(result)

  /*
    Mapping left side (exception) to Strings
   */
  val result2: Either[String, Int] = for {
    x <- eitherFromOpt
    errorString = "Error:"
    y <- eitherFromTry.leftMap(exception => s"$errorString $exception")
  } yield {
    x + y
  }
  println(result2)


  // Mapping to custom class
  case class MyException(msg: String, exception: Throwable)

  eitherFromTry.leftMap(ex => MyException("error", ex))

}
