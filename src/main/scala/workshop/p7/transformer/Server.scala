package workshop.p7.transformer

import cats.data.EitherT
import cats.implicits._
import workshop.p6.either.BetterHelloServer._

import scala.concurrent.Future

trait Server {

  import scala.concurrent.ExecutionContext.Implicits.global

  val userRepo: UserRepo
  val translationRepo: TranslationRepo

  val helloMsgCode = "_hello_"

  def welcomeUserPage(userToken: String): Future[String] = {
    welcomeUserText(userToken)
      .fold(_.reason, identity)
  }

  def welcomeUserText(userToken: String):  EitherT[Future, ServerError, String] = ???
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