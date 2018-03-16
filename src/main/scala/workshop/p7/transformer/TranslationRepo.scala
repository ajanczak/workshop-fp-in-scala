package workshop.p7.transformer

import java.util.Locale

import scala.concurrent.Future

case class TranslationKey(locale: Locale, msgCode: String)

trait TranslationRepo {
  def findByKey(key: TranslationKey): Future[Option[String]]
}

// Singleton
object TranslationRepoMapImpl extends TranslationRepo {

  val db = Map(
    TranslationKey(Locale.ENGLISH, "_hello_") -> "Hello",
    TranslationKey(Locale.ITALIAN, "_hello_") -> "Ciao"
  )

  override def findByKey(key: TranslationKey): Future[Option[String]] = {
    Future.successful(db.get(key))
  }
}