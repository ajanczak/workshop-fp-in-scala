package workshop.p2.higherfunction.example

import scala.annotation.tailrec
import scala.collection.immutable

object TailRecursion extends App {

  val list: immutable.List[Int] = List(1, 2, 3, 4, 5)
  val emptyList: immutable.List[Int] = Nil
  assert(List(1, 2) == (1 :: 2 :: Nil))

  def sumStackOverflow(list: List[Int]): Int = {

    list match {
      case Nil => 0
      case head :: tail =>
        head + sumStackOverflow(tail)
    }
  }

  sumStackOverflow(list)


  /// BIG LIST
  val bigList = List.fill(n = 10000)(elem = 7)

  // THIS THROWS StackOverflowError
  sumStackOverflow(bigList)


  ///// vs /////

  def sumTailRec(list: List[Int]): Int = {

    val zero = 0

    @tailrec
    def loop(acc: Int, list: List[Int]): Int = {

      list match {
        case Nil => acc
        case head :: tail =>
          val updatedAcc = head + acc
          loop(updatedAcc, tail)
      }
    }

    loop(zero, list)
  }

  assert(sumTailRec(list) == 15)
  sumTailRec(bigList)
}
