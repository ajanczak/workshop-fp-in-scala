package workshop.p4.option.solution

import java.util.Locale

import workshop.p4.option.{TranslationKey, TranslationRepo, UserDB, UserRepo}

private object solution {

  private trait Server {
    val userRepo: UserRepo
    val translationRepo: TranslationRepo

    val Default = "No Translation OR user not in db"
    val helloMsgCode = "_hello_"

    // TODO implement this
    def welcomeUserPage(userToken: String): String = {

      val idOpt: Option[Integer] = Option(userRepo.getUserIdByToken(userToken))

      val translationOpt = for {
        userId <- idOpt
        locale <- userRepo.getUserLocaleById(userId)
        msg <- translationRepo.findByKey(TranslationKey(locale, helloMsgCode))
      } yield {
        msg
      }
      translationOpt.getOrElse(Default)
    }
  }

  def getUserLocaleById(id: Int): Option[Locale] = {
    UserDB.getAll
      .find(_.id == id)
      .map(_.locale)
  }

}