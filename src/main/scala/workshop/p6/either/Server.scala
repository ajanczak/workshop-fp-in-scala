package workshop.p6.either

import workshop.p6.either.BetterHelloServer._
import workshop.p6.either.example.EitherExample.header

trait Server {
  val userRepo: UserRepo
  val translationRepo: TranslationRepo

  val helloMsgCode = "_hello_"

  def welcomeUserPage(userToken: String): String = {
    welcomeUserText(userToken)
      .fold(_.reason, identity)
  }

  def welcomeUserText(userToken: String): Either[ServerError, String] = ???
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

object PatternMatching extends App {
  header("PATERN MATCHING")

  val err: ServerError = NoUserWithGivenToken("er1")
  // You will get error
  err match {
    case e1: NoTranslation => "e1: "+e1.reason
  }
}