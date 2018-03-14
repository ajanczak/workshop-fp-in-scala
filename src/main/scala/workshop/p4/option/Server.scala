package workshop.p4.option

trait Server {
  val userRepo: UserRepo
  val translationRepo: TranslationRepo

  val DefaultFallback = "No Translation OR user not in db"
  val helloMsgCode = "_hello_"

  // TODO implement this
  def welcomeUserPage(userToken: String): String = {
    ???
  }
}

object HelloServer extends Server {
  override val userRepo: UserRepo = UserRepoMapImpl
  override val translationRepo: TranslationRepo = TranslationRepoMapImpl
}