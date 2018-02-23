package workshop.p1.pure.solution

import workshop.p1.pure.BankAccount

object Solution {

  private case class PureBankAccount(state: Int) extends BankAccount {

    override def addMoney(amount: Int): BankAccount = PureBankAccount(state + amount)

    override def getState: Int = state
  }


}

