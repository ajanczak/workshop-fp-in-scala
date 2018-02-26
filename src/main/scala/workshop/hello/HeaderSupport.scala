package workshop.hello

trait HeaderSupport {

  def header(header: String) = {
    (1 to 5) foreach(_ => print("-"))
    print(s" $header ")
    (1 to 5) foreach(_ => print("-"))
    println("")

  }
}
