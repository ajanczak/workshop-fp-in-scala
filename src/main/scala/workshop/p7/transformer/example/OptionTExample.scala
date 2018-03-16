package workshop.p7.transformer.example

import cats.data._
import cats.implicits._
import workshop.hello.HeaderSupport

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Try

object OptionTExample extends App with HeaderSupport {

  import scala.concurrent.ExecutionContext.Implicits.global

  val oneFOpt: Future[Option[Int]] = Future.successful(Option(1))
  val twoFOpt: Future[Option[Int]] = Future.successful(Option(2))

  val result = oneFOpt.map(_.map(int => int + 1))
  println(Await.result(result, 5 seconds))

  header("For comprehension:")

  // I can use what I know already
  val r = for {
    oneOpt <- oneFOpt
    twoOpt <- twoFOpt
  } yield {
    for {
      one <- oneOpt
      two <- twoOpt
    } yield {
      one + two
    }
  }
  println(Await.result(r, 5 seconds))

  val threeFOpt: Future[Option[Int]] = Future.successful(None)

  val r2 = for {
    oneOpt <- oneFOpt
    twoOpt <- twoFOpt
    threeOpt <- threeFOpt
  } yield {
    for {
      one <- oneOpt
      two <- twoOpt
      three <- threeOpt
    } yield {
      one + two + three
    }
  }
  println(Await.result(r2, 5 seconds))


  header("OptionT: ")

  val oneOptT: OptionT[Future, Int] = OptionT.apply(oneFOpt)
  val twoOptT: OptionT[Future, Int] = OptionT.apply(twoFOpt)

  // just one .map()
  val r3: OptionT[Future, Int] = oneOptT.map(int => int + 2)
  println(r3)

  // working with For-comprehension like before
  val r4: OptionT[Future, Int] = for {
    one <- oneOptT
    two <- twoOptT
  } yield {
    one + two
  }
  println(Await.result(r4.value, 5 seconds))

  val threeOptF = OptionT(threeFOpt)

  // working with For-comprehension like before
  val r5 = for {
    one <- oneOptT
    two <- twoOptT
    three <- threeOptF
  } yield {
    one + two + three
  }
  println(Await.result(r5.value, 5 seconds))


}
