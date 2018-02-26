package workshop.p6.either

import java.util.Locale

import org.scalatest.{FlatSpec, Matchers}


class EitherSpec extends FlatSpec with Matchers {

  "HelloServer" should "return correct values" in {
    
    import BetterHelloServer._
    import UserRepoMapImpl._

    welcomeUserText(userToken = user1.token) shouldEqual Right("Hello")
    welcomeUserText(userToken = user2.token) shouldEqual Right("Ciao")
    welcomeUserText(userToken = user3.token) shouldEqual Left(NoTranslation(TranslationKey(Locale.GERMAN, helloMsgCode)))
    welcomeUserText(userToken = "unknown")   shouldEqual Left(NoUserWithGivenToken("unknown"))
    welcomeUserText(userToken = user4.token) shouldEqual Left(NoLocaleForUser(user4.id))
  }

}