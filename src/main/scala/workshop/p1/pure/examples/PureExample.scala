package workshop.p1.pure.examples

import org.scalatest.MustMatchers.convertToEqualizer

/**
  * Basic example of what pure function is.
  */
object PureExample extends App {

  // side effects
  def impureFunction(name: String): Unit = println(s"My name is $name")

  // side effects free
  def pureFunction(name: String): String = s"My name is $name"


  /// TEST PURE:
  val expectedResult = "My name is Andrzej"

  // future method call don't brake anything
  pureFunction("Maciek")
  pureFunction("Adam")

  val resultOne = pureFunction("Andrzej")

  // result assert
  assert(resultOne === expectedResult)

  // Referential transparency assert
  assert("My name is Andrzej" === "My name is Andrzej")
  assert(pureFunction("Andrzej") === "My name is Andrzej")
  assert("My name is Andrzej" === pureFunction("Andrzej"))

  // TEST IMPURE:
  val resultImpure: Unit = impureFunction("Andrzej")
  // assert(resultImpure === ?????)
  // HOW ???
  // get somehow access to output buffer ?????!!!


  // Program 1:
  println("Program 1:")
  impureFunction("Andrzej")

  // Program 2:
  println("Program 2:")
  val result: String = pureFunction("Andrzej")
  println(result)

}
