package workshop.p3.laziness


trait TimeLogger {
  def log(value: Long): Unit
}

object SimpleTimeLogger extends TimeLogger {
  override def log(value: Long): Unit = println(value)
}

