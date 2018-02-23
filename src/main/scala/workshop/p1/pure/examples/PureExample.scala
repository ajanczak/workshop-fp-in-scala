package workshop.p1.pure.examples

import org.scalatest.MustMatchers.convertToEqualizer


object PureExample extends App {

  // side effects free
  def pureFunction(name: String): String = s"My name is $name"

  // side effects
  def impureFunction(name: String): Unit = println(s"My name is $name")


  /// TEST PURE:
  val resultOne = pureFunction("Andrzej")
  pureFunction("Maciek")
  pureFunction("Adam")

  val expectedResult = "My name is Andrzej"

  // result assert
  assert(resultOne === expectedResult)
  // Referential transparency assert
  assert(expectedResult === pureFunction("Andrzej"))

  // TEST IMPURE:
  val resultImpure: Unit = impureFunction("Andrzej")
  // assert(resultImpure === ?????)
  // HOW ???
  // get somehow access to output buffer ?????!!!

}
