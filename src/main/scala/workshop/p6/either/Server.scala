package workshop.p6.either

import cats.implicits._
import workshop.p6.either.BetterHelloServer._

trait Server {
  val userRepo: UserRepo
  val translationRepo: TranslationRepo

  val helloMsgCode = "_hello_"

  def welcomeUserPage(userToken: String): String = {
    welcomeUserText(userToken)
      .fold(_.reason, identity)
  }

  def welcomeUserText(userToken: String): Either[ServerError, String] = {

    val idOpt = Option(userRepo.getUserIdByToken(userToken))

    for {
      id <- Either.fromOption(
        o = idOpt,
        ifNone = NoUserWithGivenToken(userToken)
      )
      locale <- Either.catchNonFatal{
        userRepo.getUserLocaleById(id)
      }.leftMap(ex => NoLocaleForUser(id))

      tk = TranslationKey(locale, helloMsgCode)

      text <- Either.fromOption(translationRepo.findByKey(tk),
        NoTranslation(tk)
      )

    } yield {
      text
    }
  }
}

object BetterHelloServer extends Server {
  override val userRepo: UserRepo = UserRepoMapImpl
  override val translationRepo: TranslationRepo = TranslationRepoMapImpl

  sealed trait ServerError { def reason: String}
  case class NoUserWithGivenToken(token: String) extends ServerError {
    override def reason: String = s"No user with token [$token]"
  }
  case class NoTranslation(translationKey: TranslationKey) extends ServerError {
    override def reason: String = s"No translation for key [$translationKey]"
  }
  case class NoLocaleForUser(userId: Int) extends ServerError {
    override def reason: String = s"No Locales for user $userId"
  }
}