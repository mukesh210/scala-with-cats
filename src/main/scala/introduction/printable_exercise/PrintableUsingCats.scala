package introduction.printable_exercise

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

import java.util.Date     // Import Interface Syntax which allows calling .show directly

object PrintableUsingCats extends App {
  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]

  val intAsString: String = showInt.show(42)
  val stringAsString: String = showString.show("Life")

  println(intAsString)
  println(stringAsString)

  // need Show[Int] to be in scope for implicit resolution
  println(123.show) // would need Show[Int] in implicit scope
  println("wonderful".show) // would need Show[String] in implicit scope

  // defining custom instances
  // 1. Create instance of Show[Date]
  /*implicit val dateShow: Show[Date]  = new Show[Date] {
    override def show(date: Date): String =
      s"${date.getTime}ms since the epoch"
  }*/

  // 2. Utilize methods provided by companion
  implicit val dateShow1: Show[Date] = Show.show(date => s"${date.getTime}ms since the epoch")

  println(new Date().show)

  //--------------------- Print Cat using Cats library
  import cats.Show
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.show._

  val cat = Cat("alpha-x", 2, "Red")

  implicit val catShow: Show[Cat] = Show.show{ cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show

    s"${name} is a ${age} year-old ${color} cat"
  }

  // use show interface syntax to print our instance of Cat
  println(cat.show)
}
