package workshop.p3.laziness.extra

import scala.concurrent.Future
import scala.util.Random


//TODO: define and implement retry method
object Retry {
  // def retry.... = ???
}

object RetryUsage {
  /**
    * Simulate insecure API
    * @return
    */
  def evenOrError(): Int = {
    val number = Random.nextInt()
    number % 2 match {
      case 0 => number
      case _ => throw new Exception(s"Not even number: $number")
    }
  }

//TODO: Uncomment and implement
//  Retry.retry(5){
//    evenOrError()
//  }


  def futureRetry[T](times: Int)(f: Future[T]) = {

  }
}