package workshop.p4.option

import org.scalatest.{FlatSpec, Matchers}


class OptionSpec extends FlatSpec with Matchers {

  "HelloServer" should "return correct values" in {

    import UserDB._

    HelloServer.welcomeUserPage(user1.token) shouldEqual "Hello"
    HelloServer.welcomeUserPage(user2.token) shouldEqual "Ciao"
    // user exists but no translation
    HelloServer.welcomeUserPage(user3.token) shouldEqual HelloServer.DefaultFallback
    // user with this token don't exists
    HelloServer.welcomeUserPage("no way") shouldEqual HelloServer.DefaultFallback

  }

}