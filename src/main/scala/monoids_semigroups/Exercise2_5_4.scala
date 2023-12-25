package monoids_semigroups

import cats.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.syntax.semigroup._

object Exercise2_5_4 extends App {

  // add function to add List[Int]
  def addV1(items: List[Int]): Int = {
    items.foldLeft(Monoid[Int].empty)(_ |+| _)
  }

  val items = List(10, 20, 30, 40, 50)
  println(addV1(items))

  // improvise add function to support both List[Int] & List[Option[Int]]
  def addV2[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    items.foldLeft(monoid.empty)(_ |+| _)
  }

  // improvised addV2 to use Context Bounds
  def addV3[A : Monoid](items: List[A]): A = {
    items.foldLeft(Monoid[A].empty)(_ |+| _)
  }

  val optionalItems = List(Some(5), Some(10), None, None)
  println(s"Using addV2: ${addV2(items)}")
  println(s"Using addV3: ${addV3(optionalItems)}")

//  val optionalItems1 = List(Some(10), Some(20))
//  println(s"using addV3: ${addV3(optionalItems1)}")

  case class Order(totalCost: Double, quantity: Double)

  import cats.instances.double._
  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0d, 0d)

    override def combine(x: Order, y: Order): Order =
      Order(x.totalCost |+| y.totalCost, x.quantity |+| y.quantity)
  }

  val listOfOrders = List(Order(10.2, 5), Order(5.6, 4))
  println(addV3(listOfOrders))
}
