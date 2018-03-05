package workshop.p1.pure.examples

object ClassExample extends App {


  // class + constructor + field access + trait impl
  class ExampleVal(val fieldOne: String) extends Serializable

  val exampleVal = new ExampleVal("example val")
  exampleVal.fieldOne


  //////  OR  /////

  case class ExampleCaseClass(fieldOne: String) extends Serializable

  val exampleCaseClass: ExampleCaseClass = ExampleCaseClass("example case class")
  exampleCaseClass.fieldOne

}
