package workshop.p3.laziness.example

object LazyVal extends App {

  println("START")

  lazy val x = {
    println("x evel")
    1
  }

  val y = {
    println("y eval")
    2
  }

  x
  y

  x
  y

  println("END")
}
