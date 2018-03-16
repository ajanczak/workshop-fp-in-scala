package workshop.p1.pure.examples

import workshop.hello.HeaderSupport

import scala.collection.mutable

object Immutability extends App with HeaderSupport {

  val expected = List(1, 2, 3, 4)

  val threeElemList = mutable.MutableList(1, 2, 3)
  val threeElemListImmutable = List(1, 2, 3)

  println(threeElemList.+=(4))
  println(threeElemList.+=(4))

  /**
    * Some long block of code
    *
    *
    *
    *
    *
    */

  println((threeElemList.+=(4)).size == 4) // brakes referential transparency


  val typeOfList: List[Int] = threeElemListImmutable

  assert((threeElemListImmutable :+ (4)).size == 4)
  assert((threeElemListImmutable :+ (4)).size == 4)

  println {
    (threeElemListImmutable :+ 4) :+ 5
  }
  println {
    threeElemListImmutable :+ 4 :+ 5
  }

  println {
    threeElemListImmutable
      .filter(x => x % 2 == 0)
      .map(_ * 2)
  }

}
