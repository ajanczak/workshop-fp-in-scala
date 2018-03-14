package workshop.p1.pure.examples

import scala.collection.mutable

object Immutability extends App {

  val expected = List(1, 2, 3, 4)

  val threeElemList = mutable.MutableList(1 , 2, 3)
  println(threeElemList.+=(4))
  println(threeElemList.+=(4))

  List(1, 2, 3, 4) == expected
  println(threeElemList.+=(4) == expected) // brakes referential transparency


  val list = List(1, 2, 3)
  assert(list :+(4) == expected)
  assert(list :+(4) == expected)

  println {
    (list :+ 4) :+ 5
  }

  println{
    list
      .filter(x => x % 2 == 0)
      .map(_ * 2)
  }
}
