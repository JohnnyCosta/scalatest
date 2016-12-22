class MyBox2[T](val value:T) {
  override def toString() = "MyBox2("+value+")"
}

class MyBox2Wrapper[T](w:MyBox2[T]) {
  def flatMap[R](f:T=>MyBox2[R]) = map(f).value
  def map[R](f:T=>R) = new MyBox2(f(w.value))
}

object TestMonadWrapper {
  implicit def myBoxToWrapper[S](mb:MyBox2[S]) = new MyBox2Wrapper[S](mb)

  def main(args:Array[String]) {
    val ma = new MyBox2("hello world")
    println ( ma.map((a) => a.length) )

    val res = for {
      a <- ma
    } yield a.length + 2

    println(res)

    val mb = new MyBox2("hola mundo")

    val res2 = for {
      a <- ma
      b <- mb
    } yield a.size + b.size

    println (res2)
  }
}