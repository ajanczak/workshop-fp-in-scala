package workshop.p4.option.example

import workshop.hello.HeaderSupport

import scala.util.Try

object OptionExamples extends App with HeaderSupport {

  def getLanguage(s: String): String = {
    if (s.contentEquals("opcja")) "PL"
    else if (s.contentEquals("option")) "EN"
    else null
  }

  val whatIsThis: String = getLanguage("Me gusto !")

  println("This is:"+whatIsThis)

  // Do it like this:
  println(Option(whatIsThis))

  // NEVER like that !!! :
  println(Some(whatIsThis))
  // why ?:
  println(Some(null))

  // Putting value into the "box"
  val emptyOpt: Option[String] = Option.apply(whatIsThis)

  println(Try {
    emptyOpt.get
  })
  println(emptyOpt.getOrElse("DE"))
  println(emptyOpt.map("this language is: " + _))


  header("SOME")

  val langOpt: Option[String] = Option(getLanguage("opcja"))
  println(Try {
    langOpt.get
  })
  println(langOpt.getOrElse("DE"))
  println(langOpt.map("this language is: "+ _ ))


  header("PATTERN MATCHING")
  langOpt match {
    case Some(value) => println(s"There is some value here: $value")
    case None => println("I have None")
  }

  ////// Map / Flat Map //////
  header("FLAT MAP")

  def getCountryName(code: String): Option[String] = {
    code match {
      case "PL" => Some("Polska")
      case "DE" => Some("Deutschland")
      case _ => None
    }
  }
  val doubleOpt: Option[Option[String]] = langOpt.map(code => getCountryName(code))
  println(doubleOpt)
  println(doubleOpt.flatten)

  val countryOpt = langOpt.flatMap(code => getCountryName(code))
  println(countryOpt)

  header("FOR COMPREHENSION")

  val result = for {
    langCode <- langOpt
    country <- getCountryName(langCode)
  } yield {
    country
  }
  println(result)

  val sum = for {
    x <- Some(3)
    y <- Some(4)
    z <- Some(5)
  } yield {
    x + y + z
  }
  println(sum)

}
