package workshop.p1.pure

trait BankAccount {

  def addMoney(amount: Int): BankAccount

  def getState: Int

}
