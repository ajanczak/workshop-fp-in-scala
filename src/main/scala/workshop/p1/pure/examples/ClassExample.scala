package workshop.p1.pure.examples

object ClassExample extends App {

  trait Person {
    def getName: String
  }


  // class + constructor + field access + trait impl
  class SimplePerson(val name: String) extends Person {
    override def getName: String = {
      name
    }
  }
  val person = new SimplePerson("Andrzej")
  println(person.name)


  //////  OR  /////


  case class CasePerson(name: String) extends Person {
    override def getName: String = name
  }

  val casePerson: CasePerson = CasePerson("Andrew")
  println(casePerson.name)

}
