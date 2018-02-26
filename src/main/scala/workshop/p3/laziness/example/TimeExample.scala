package workshop.p3.laziness.example

object TimeExample extends App {

  val t0 = System.currentTimeMillis()

  1 to 10000 map { i => i * i }

  val t1 = System.currentTimeMillis()

  val diff = t1 - t0

  println(diff+" ms passed")
}
