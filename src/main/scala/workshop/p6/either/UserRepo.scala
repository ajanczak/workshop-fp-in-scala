package workshop.p6.either

import java.util.Locale

import com.sun.istack.internal.Nullable

trait UserRepo {

  // CAUTION: can return null
  @Nullable
  def getUserIdByToken(token: String): Integer

  @throws[Exception]
  def getUserLocaleById(id: Int): Locale
}

object UserRepoMapImpl extends UserRepo {

  case class User(id: Int, token: String, locale: Locale)

  val user1 = User(1, "aaa", Locale.ENGLISH)
  val user2 = User(2, "bbb", Locale.ITALIAN)
  val user3 = User(3, "ccc", Locale.GERMAN)
  // corrupted Data !
  val user4 = User(-1, "ddd", Locale.CHINA)

  val useres = Set(user1, user2, user3, user4)

  /**
    * Please don't modify this method. It's example of unreliable interface.
    * Can return null !
    */
  @Nullable
  override def getUserIdByToken(token: String): Integer = {
    val idOpt =
      useres
        .find(_.token == token)
        .map(_.id)

    if (idOpt.isDefined)
      return idOpt.get
    else
      return null
  }

  @throws[Exception]
  override def getUserLocaleById(id: Int): Locale = {
    useres
      .filter(_.id > 0) // simulate corrupted data
      .find(_.id == id).get.locale
  }

}