package workshop.p3.laziness.example

object LazyParam extends App {

  println("START")

  // call by value
  def strict(param: String): String = {
    println("strict body")
    param
  }

  // call by name
  def lazyFun(param: => String): String = {
    println("lazy body")
    param // param initialization
  }

  strict {
    println("strict eval")
    "strict value"
  }

  lazyFun {
    println("lazy eval")
    "lazy value"
  }

  println("END")
}
