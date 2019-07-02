

abstract class AbstractClass {
  def message: String = "Hello from Abstract class"
}

trait Trait1 {
  def message: String = "Hello from trait 1"
}

trait Trait2 {
  def message: String = "Hello from trait 2"

}


class MixinClass extends AbstractClass with Trait1 with Trait2 {
  override def message: String = super.message
}


object MainTest {
  def main(args: Array[String]): Unit = {
    val melange = new MixinClass()
    println(melange.message)
  }
}
