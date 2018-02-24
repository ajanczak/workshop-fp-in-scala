package workshop.p1.pure

import org.scalatest.{FlatSpec, MustMatchers}

import scala.collection.immutable
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Random

class PureTest extends FlatSpec with MustMatchers {

  //TODO: After you're done with implementation please change it to PureBankAccount
  def newBankAccount(): BankAccount = new DirtyBankAccount

  "BankAccount implementation" should "be idempotent" in {

    val bankAccountWithNoMoney: BankAccount = newBankAccount()

    bankAccountWithNoMoney.addMoney(10)
    bankAccountWithNoMoney.addMoney(10)
    bankAccountWithNoMoney.addMoney(10)
    val result: BankAccount = bankAccountWithNoMoney.addMoney(10)

    result.getState mustEqual 10

  }

  it should ("work correctly") in {

    val bankAccount: BankAccount = newBankAccount()
    val bankAccountAfter =
      bankAccount
        .addMoney(1)
        .addMoney(2)
        .addMoney(3)

    bankAccountAfter.getState mustEqual 6

  }

  it should ("be referential transparent") in {
    val zero: BankAccount = newBankAccount()

    val r1 = zero.addMoney(2).getState + zero.addMoney(1).getState
    val r2 = 0 + 2 + 0 + 1

    r1 mustEqual r2
  }

  it should ("be thread safe") in {

    import scala.concurrent.ExecutionContext.Implicits.global

    val zeroAccount: BankAccount = newBankAccount()

    val eventualBankAccounts: immutable.Seq[Future[BankAccount]] =
      (0 to 9).map { i =>
        Future {
          Thread.sleep(Random.nextInt(100))
          zeroAccount.addMoney(i)
        }
      }

    val bankAccounts = eventualBankAccounts.map { future =>
      Await.result(future, 5 seconds)
    }

    bankAccounts.zipWithIndex.map {
      case (ba, index) => ba.getState mustEqual index
    }
  }

}
