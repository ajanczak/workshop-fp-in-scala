package workshop.p2.higherfunction

import org.scalatest.{FlatSpec, Matchers}

class HigherSpec extends FlatSpec with Matchers {

  val listOfInts = List(1, 2, 3, 4, 5)
  val listOfStrings: List[String] = List("Scala", "Java", "Python")

  "filterList" should "filter elements correctly" in {

    val r = HigherFunctions.filterList(listOfInts)(_ > 2)

    r.foreach(x => x shouldBe >(2))
  }

  "filterList" should "also work with List[String]" in {

    val r = HigherFunctions.filterList(listOfStrings)(_.contains('a'))

    r.foreach(x => x should contain ('a'))
  }

  "filterList" should "work with empty list" in {

    val emptyList = List.empty[Int]
    val r = HigherFunctions.filterList(emptyList)(_ > 2)
    r shouldEqual List.empty
  }


  "reduceList" should "work with Ints" in {

    val sumReduce = HigherFunctions.reduceList(zero = 0, list = listOfInts)((x, y) => x + y)
    sumReduce shouldEqual 15

    val reduceLikeMultiplication = HigherFunctions.reduceList(zero = 1, list = listOfInts)(reduceFun = _ * _)
    reduceLikeMultiplication shouldEqual 120
  }

  "reduceList" should "work with Strings" in {

    val sumReduce = HigherFunctions.reduceList(zero = "", list = listOfStrings)( (s1, s2) => s1 + s2)
    sumReduce shouldEqual listOfStrings.mkString
  }


}