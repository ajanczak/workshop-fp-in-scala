package workshop.p7.transformer.example

import cats.data.EitherT
import cats.implicits._
import workshop.hello.HeaderSupport
import workshop.p7.transformer.example.OptionTExample.r5

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Try

object EitherTExample extends App with HeaderSupport {

  import scala.concurrent.ExecutionContext.Implicits.global

  val oneFE: Future[Either[String, Int]] = Future(Either.right(1))
  val twoFE: Future[Either[String, Int]] = Future(Either.right(2))

  header("EitherT: ")

  val oneETF  = EitherT.apply(oneFE)
  val twoETF = EitherT.apply(twoFE)

  val r3: EitherT[Future, String, Int] = oneETF.map(int => int + 2)
  println(r3)

  val r4 = for {
    one <- oneETF
    two <- twoETF
  } yield {
    one + two
  }
  println(Await.result(r4.value, 5 seconds))

}
