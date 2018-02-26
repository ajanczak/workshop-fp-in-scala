package workshop.p2.higherfunction

import scala.annotation.tailrec

object HigherFunctions {

  def filterList[T](list: List[T])(predicate: T => Boolean): List[T] = {

    // @tailrec
    def loop(acc: List[T], list: List[T]): List[T] = {
      ???
    }

    loop(Nil, list)
  }

  // @tailrec
  def reduceList[T](zero: T, list: List[T])(reduceFun: (T, T) => T): T = ???

}
