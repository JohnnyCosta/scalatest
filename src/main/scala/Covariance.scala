import java.util

abstract class Animal {
  def name: String
}

case class Cat(name: String) extends Animal

case class Dog(name: String) extends Animal

// Contravariance type
abstract class Printer[-A] {
  def print(value: A): Unit
}

class AnimalPrinter extends Printer[Animal] {
  def print(animal: Animal): Unit =
    println("The animal's name is: " + animal.name)
}

class CatPrinter extends Printer[Cat] {
  def print(cat: Cat): Unit =
    println("The cat's name is: " + cat.name)
}

class DogPrinter extends Printer[Dog] {
  def print(dog: Dog): Unit =
    println("The dog's name is: " + dog.name)
}


object CovarianceTest extends App {

  // List is covariance: List[+A]
  def printFromListOfAnimals(animals: List[Animal]): Unit = {
    animals.foreach { animal =>
      println(animal.name)
    }
  }

  // Stack by default, aka invariant
  def printFromStackOfAnimals(animals: util.Stack[Animal]): Unit = {
    animals.forEach { animal =>
      println(animal.name)
    }
  }

  def printACat(printer: Printer[Cat], cat: Cat): Unit = {
    printer.print(cat)
  }

  def printADog(printer: Printer[Dog], dog: Dog): Unit = {
    printer.print(Dog)
  }


  val animalsList = List(Cat("a cat"), Dog("a dog"))
  val catsList: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
  val dogsList: List[Dog] = List(Dog("Fido"), Dog("Rex"))

  printFromListOfAnimals(animalsList)
  printFromListOfAnimals(catsList)
  printFromListOfAnimals(dogsList)

  val animalsStack = new util.Stack[Animal]
  animalsStack.push(Cat("a cat"))
  animalsStack.push(Cat("a dog"))
  val catsStack = new util.Stack[Cat]
  catsStack.push(Cat("Whiskers"))
  catsStack.push(Cat("Tom"))
  val dogsStack = new util.Stack[Dog]
  dogsStack.push(Dog("Fido"))
  dogsStack.push(Dog("Rex"))

  printFromStackOfAnimals(animalsStack)
  //  printFromStackOfAnimals(catsStack) !! Do not compile since it is invariant !!
  //  printFromStackOfAnimals(dogsStack)

  val aCat = Cat("a cat")
  val aDog = Dog("a dog")
  val catPrinter: Printer[Cat] = new CatPrinter
  val animalPrinter: Printer[Animal] = new AnimalPrinter
  val dogPrinter: Printer[Dog] = new DogPrinter
  printACat(catPrinter, aCat)
  printACat(animalPrinter, aCat) //  !! Possible since it is Contravariance !!
  //  printACat(dogPrinter,aCat) !! Cannot !!
  //  printADog(catPrinter,aDog) !! Cannot !!
  printADog(animalPrinter, aDog) //  !! Possible since it is Contravariance !!
  printADog(dogPrinter, aDog)


}
