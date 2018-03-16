package workshop.p1.pure.examples

object ReferentialTransparency extends App {

  var adder = 2

  def addMyNumber(x: Int): Int = {
    x + adder
  }

  // here it's easy
  val r1 = addMyNumber(1)

  // block of hard to understand code modify adder var
  for {i <- 1 to 10} {

    if (i < 3)
      adder = i * i

    for {j <- 1 to 10} {
      if (j == 2) adder = j-1
      else {
        if (i<j) adder = i*j
        else adder = j*j
      }
    }
  }

  // here it's not
  val r2 = addMyNumber(1)

  println(s"r1: $r1, r2: $r2")


  ///// VS /////

  def addMyNumberPure(x: Int): Int = {
    x + 2
  }


}
