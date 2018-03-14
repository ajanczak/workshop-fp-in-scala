package workshop.p4.option.example

object ListExample {

  case class User(id: Int, name: String)

  val db = List(
    User(1, "Andrzej"),
    User(2, "Maciek")
  )

  def getNameById(id: Int): Option[String] = {
    val userOpt = db.find(user => user.id == id)
    val nameOpt = userOpt.map(_.name)
    nameOpt
  }
}
