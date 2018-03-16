package workshop.p6.either.solution

import java.util.Locale

import workshop.p6.either.BetterHelloServer.{NoLocaleForUser, NoTranslation, NoUserWithGivenToken, ServerError}
import workshop.p6.either.{TranslationKey, TranslationRepo, UserRepo}
import cats.implicits._

import scala.util.Try

private object Solution {

  private trait Server {
    val userRepo: UserRepo
    val translationRepo: TranslationRepo

    val helloMsgCode = "_hello_"

    def welcomeUserPage(userToken: String): String = {
      welcomeUserText(userToken)
        .fold(_.reason, identity)
    }

    def welcomeUserText(userToken: String): Either[ServerError, String] = {

      val idOpt: Option[Integer] = Option(userRepo.getUserIdByToken(userToken))
      val userIdE = Either.fromOption(
        o = idOpt,
        ifNone = NoUserWithGivenToken(userToken)
      )

      def localeE(id: Int): Either[NoLocaleForUser, Locale] =
        Either.fromTry {
          Try(userRepo.getUserLocaleById(id))
        }.leftMap(ex => NoLocaleForUser(id))

      for {
        id <- userIdE
        locale <- localeE(id)
        tk = TranslationKey(locale, helloMsgCode)
        text <- Either.fromOption(
          translationRepo.findByKey(tk),
          ifNone = NoTranslation(tk)
        )

      } yield {
        text
      }
    }
  }

}
