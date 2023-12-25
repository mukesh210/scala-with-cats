package monoids_semigroups

import cats.Monoid
import cats.Semigroup

object MonoidsInCats extends App {

  // 1. Using Type Class apply method defined in companion object
  import cats.instances.string._
  val m1 = Monoid[String].combine("Hi", " There!")
  println(s"m1: ${m1}")
  // since Monoid extends SemiGroup, we can write:
  val m2 = Semigroup[String].combine("Hi", " There!")
  println(s"m2: ${m2}")

  import cats.instances.int._
  val m3 = Monoid[Int].combine(22, 22)
  println(s"m3: ${m3}")

  import cats.instances.int._ // needed for below operation since OptionMonoid will use internally
  import cats.instances.option._
  val m4 = Monoid[Option[Int]].combine(Some(12), None)
  println(s"m4: ${m4}")

  // 2. Using Syntax
  import cats.instances.string._
  import cats.syntax.semigroup._
  val m5 = "Hi" |+| " There!" |+| Monoid[String].empty
  println(s"m5: ${m5}")
  val m6 = "Hi" |+| " There!"
  println(s"m6: ${m6}")

  import cats.instances.int._
  import cats.syntax.semigroup._
  val m7 = 12 |+| 23
  println(s"m7: ${m7}")
  val m8 = 12 |+| 23 |+| Monoid[Int].empty
  println(s"m8: ${m8}")

  // add 2 maps such that values are added for same key
  val map1 = Map("a" -> 1, "b" -> 2)
  val map2 = Map("c" -> 3, "d" -> 4, "b" -> 5)
  import cats.instances.map._
  val m9 = map1 |+| map2
  println(s"m9: ${m9}")

  val t1 = ("hello", 12)
  val t2 = ("world", 23)
  import cats.instances.tuple._
  val m10 = t1 |+| t2
  println(s"m10: ${m10}")
}
