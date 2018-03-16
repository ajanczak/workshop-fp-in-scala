package workshop.p7.transformer

import java.util.Locale

import scala.concurrent.Future

trait UserRepo {

  def getUserIdByToken(token: String): Future[Option[Int]]

  def getUserLocaleById(id: Int): Future[Option[Locale]]
}

object UserRepoMapImpl extends UserRepo {

  case class User(id: Int, token: String, locale: Locale)

  val user1 = User(1, "aaa", Locale.ENGLISH)
  val user2 = User(2, "bbb", Locale.ITALIAN)
  val user3 = User(3, "ccc", Locale.GERMAN)
  // corrupted Data !
  val user4 = User(-1, "ddd", Locale.CHINA)

  private val useres = Set(user1, user2, user3, user4)

  /**
    * Please don't modify this method. It's example of unreliable interface.
    * Can return null !
    */
  override def getUserIdByToken(token: String): Future[Option[Int]] = {
    val idOpt =
      useres
        .find(_.token == token)
        .map(_.id)
    Future.successful(idOpt)
  }

  override def getUserLocaleById(id: Int): Future[Option[Locale]] = {
    val localeOpt = useres
      .filter(_.id > 0) // simulate corrupted data
      .find(_.id == id)
      .map(_.locale)
    Future.successful(localeOpt)
  }

}