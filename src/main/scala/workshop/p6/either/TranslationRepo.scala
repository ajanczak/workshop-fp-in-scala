package workshop.p6.either

import java.util.Locale

case class TranslationKey(locale: Locale, msgCode: String)

trait TranslationRepo {
  def findByKey(key: TranslationKey): Option[String]
}

// Singleton
object TranslationRepoMapImpl extends TranslationRepo {

  val db = Map(
    TranslationKey(Locale.ENGLISH, "_hello_") -> "Hello",
    TranslationKey(Locale.ITALIAN, "_hello_") -> "Ciao"
  )

  override def findByKey(key: TranslationKey): Option[String] = {
    db.get(key)
  }
}