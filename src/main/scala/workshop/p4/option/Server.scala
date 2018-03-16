package workshop.p4.option

trait Server {
  val userRepo: UserRepo
  val translationRepo: TranslationRepo

  val DefaultFallback = "No Translation OR user not in db"
  val helloMsgCode = "_hello_"

  // TODO: Change this imperfect implementation !
  def welcomeUserPage(userToken: String): String = {
    val userId = userRepo.getUserIdByToken(userToken)
    val locale = userRepo.getUserLocaleById(userId).get
    val tk = TranslationKey(locale, helloMsgCode)
    translationRepo.findByKey(tk).get
  }
}

object HelloServer extends Server {
  override val userRepo: UserRepo = UserRepoMapImpl
  override val translationRepo: TranslationRepo = TranslationRepoMapImpl
}