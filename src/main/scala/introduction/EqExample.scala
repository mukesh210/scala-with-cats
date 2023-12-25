package introduction

import cats.Eq
import cats.instances.int._
import cats.instances.option._
import introduction.printable_exercise.Cat

import java.util.Date  // type instances for Eq

object EqExample extends App {
  val eqInt: Eq[Int] = Eq[Int]

  println(eqInt.eqv(123, 123))
  println(eqInt.eqv(123, 124))

  // will give compilation error
//  println(eqInt.eqv(123, Some(123)))

  import cats.syntax.eq._ // will provide us with === and =!= method
  println(123 === 123)
//  println(123 =!= Some(123))

  println( (Some(123): Option[Int]) === (Option(123): Option[Int]))
  println(Option(123) === Option(123))
  println(Option(1) === Option.empty[Int])

  import cats.syntax.option._
  println(1.some === none[Int])
  println(1.some =!= none[Int])

  // COMPARING CUSTOM TYPES
  import cats.instances.long._
  implicit val dateEq: Eq[Date] = Eq.instance[Date] { (date1, date2) => date1.getTime === date2.getTime }
  val timeX = new Date()
  println(s"timeX === timeX: ${timeX === timeX}")
  val timeY = new Date()
  println(s"timeX === timeY: ${timeX === timeY}")

  // Eq implementation for CAT case class
  import cats.instances.string._
  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
    cat1.color === cat2.color && cat1.age === cat2.age && cat1.name === cat2.name
  }
  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")
  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(s"cat1 === cat1: ${cat1 === cat1}")
  println(s"cat1 === cat2: ${cat1 === cat2}")

  println(s"optionCat1 === optionCat2: ${optionCat1 === optionCat2}")
  println(s"optionCat1 =!= optionCat2: ${optionCat1 =!= optionCat2}")
}
