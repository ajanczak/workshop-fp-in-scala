package workshop.p1.pure


/**
  * Wrong, impure implementation of BankAccount
  */
class DirtyBankAccount extends BankAccount {

  private var state = 0

  def addMoney(amount: Int): DirtyBankAccount = {
    state = state + amount
    this
  }

  def getState: Int = state
}
