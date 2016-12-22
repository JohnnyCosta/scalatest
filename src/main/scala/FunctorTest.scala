class MyBox[T](val value: T)

object FunctorTest extends App {

  //  ( A=>B )      => ( C[A]=>C[B] )   | Functor
  //    ( A=>C[B] ) => ( C[A]=>C[B] )   | Monad
  //    ( C[A=>B] ) => ( C[A]=>C[B] )   | Applicative

  // Functor
  def map[A, B](rawfunc: A => B): MyBox[A] => MyBox[B] = (a: MyBox[A]) => new MyBox(rawfunc(a.value))

  val boxedstring: MyBox[String] = new MyBox("Hello")

  def rawLengthOf(a: String): Int = a.length

  val transformedLenghtOf = map(rawLengthOf)

  val result: MyBox[Int] = transformedLenghtOf(boxedstring)

  println(result.value)

  // ---------------------------------------------

  def lengthOf(a: String) = new MyBox(a.length) // a function which takes a raw type but boxes the result itself

  // Nomad
  def flatMap[A, B](func: A => MyBox[B]): MyBox[A] => MyBox[B] = (a: MyBox[A]) => func(a.value)

  val transformedLenghtOf2 = flatMap(lengthOf) // applying the transformation, so we get our new function

  val result2: MyBox[Int] = transformedLenghtOf2(boxedstring) // applying the new function

  println(result2.value)

  // ---------------------------------------------

  val boxedLengthOf: MyBox[String => Int] = new MyBox(rawLengthOf _)

  def apply[A, B](b: MyBox[A => B]): MyBox[A] => MyBox[B] = (a: MyBox[A]) => new MyBox(b.value(a.value))

  val transformedLenghtOf3 = apply(boxedLengthOf)
  val result3: MyBox[Int] = transformedLenghtOf3(boxedstring) // applying the new function
  println(result3.value)

}