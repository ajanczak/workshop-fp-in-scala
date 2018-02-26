package workshop.p2.higherfunction.example

import org.scalatest.MustMatchers.convertToEqualizer

object HigherOrderFunctionExample extends App {

  // a pure, solid method :)
  def multiplyByTwo(x: Int): Int = x * 2

  // can be changed to function !
  val multiplyByTwoFunction: Function1[Int, Int] = multiplyByTwo _

  // other equivalent declaration
  val multiplyFun: Int => Int =
    (x: Int) => 2 * x

  // mapper function
  def mapper(list: List[Int], function: Int => Int): List[Int] = {
    for {
      element <- list
    } yield {
      function.apply(element)
    }
  }

  val numbers = List(1, 2, 3)
  val expected = List(2, 4, 6)

  val mapped: List[Int] = mapper(numbers, multiplyByTwoFunction)
  assert(mapped === expected)
  println(mapped)


  ////// ^this OR:  //// :

  val r2 = numbers.map(_ * 2)
  assert(r2 == expected)


  ///// Curring /////

  def multiplyMethod(times: Int)(number : Int): Int = times *  number

  val multiplyFunction: Int => Int => Int = multiplyMethod _

  val multiplier: Int => Int => Int =  {
    (x: Int) =>
      (y: Int) =>  x * y
  }
  val multiplierV2: Int => (Int => Int) = (x) => (y) => x*y
  assert(multiplier.apply(3).apply(2) == 6)
  assert(multiplier.apply(4).apply(2) == 8)
  assert(multiplierV2(7)(2) == 14)

  val m2 = multiplier(2)
  val m3 = multiplier(3)

  val m6 = m2 compose m3
  val m6v2 = m2 andThen m3

  assert(m6(6) == 36)
  assert(m6v2(6) == 36)


  val r = mapper(numbers, multiplyFunction(2))
  assert(r == expected)
}
