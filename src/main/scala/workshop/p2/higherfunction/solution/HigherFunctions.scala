package workshop.p2.higherfunction.solution

import scala.annotation.tailrec

private object Solution {

  def filterList[T](list: List[T])(predicate: T => Boolean): List[T] = {

    @tailrec
    def loop(acc: List[T], list: List[T]): List[T] = {
      list match {
        case head :: tail => {
          val updatedList =
            if (predicate(head)) acc.:+(head)
            else acc
          loop(updatedList, tail)
        }
        case Nil => acc
      }
    }

    loop(Nil, list)
  }

  def reduceList[T](zero: T, list: List[T])(reduceFun: (T, T) => T): T = {

    @tailrec
    def loop(acc: T, list: List[T]): T = list match {
      case Nil => acc
      case head :: tail =>
        val updatedAcc = reduceFun(acc, head)
        loop(updatedAcc, tail)
    }

    loop(zero, list)
  }


}
