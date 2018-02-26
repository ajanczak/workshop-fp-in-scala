package workshop.p6.either.solution

import workshop.p6.either.BetterHelloServer.{NoLocaleForUser, NoTranslation, NoUserWithGivenToken, ServerError}
import workshop.p6.either.{TranslationKey, TranslationRepo, UserRepo}

import cats.implicits._

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

      val idOpt = Option(userRepo.getUserIdByToken(userToken))

      for {
        id <- Either.fromOption(
          o = idOpt,
          ifNone = NoUserWithGivenToken(userToken)
        )
        locale <- Either.catchNonFatal {
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

}
