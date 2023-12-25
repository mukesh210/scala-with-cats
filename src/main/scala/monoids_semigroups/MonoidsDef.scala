package monoids_semigroups

trait SemiGroup_Custom[A] {
  def combine(x: A, y: A): A
}

trait Monoid_Custom[A] extends SemiGroup_Custom[A] {
  def empty: A
}

object Monoid_Custom {
  def apply[A](implicit monoid: Monoid_Custom[A]): Monoid_Custom[A] = monoid
}

/*
  All vals in BooleanMonoids are Monoids since they obey associativity and have
  identity
 */
object BooleanMonoids {
  implicit val booleanAndMonoid: Monoid_Custom[Boolean] = new Monoid_Custom[Boolean] {
    override def empty: Boolean = true
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val booleanOrMonoid: Monoid_Custom[Boolean] = new Monoid_Custom[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val booleanXorMonoid: Monoid_Custom[Boolean] = new Monoid_Custom[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean =
      (!x && y) || (x && !y)
  }

  // opposite of XOR
  implicit val booleanXnorMonoid: Monoid_Custom[Boolean] = new Monoid_Custom[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean =
      (!x || y) && (x || !y)
  }
}

object SetMonoids {
  // set union forms a Monoid with Empty Set as Identity
  implicit def setMonoid[T]: Monoid_Custom[Set[T]] = new Monoid_Custom[Set[T]] {
    override def empty: Set[T] = Set.empty[T]

    override def combine(x: Set[T], y: Set[T]): Set[T] = x union y
  }

  // set intersection doesn't form monoid but forms semigroup
  // because identity law doesn't holds true for intersection operation
  implicit def setIntersectionMonoidSemiGroup[T]: SemiGroup_Custom[Set[T]] = new SemiGroup_Custom[Set[T]] {
    override def combine(x: Set[T], y: Set[T]): Set[T] = x intersect y
  }
}

object Client extends App {
  import SetMonoids._
  val intMonoid = Monoid_Custom[Set[Int]]
  val stringMonoid = Monoid_Custom[Set[String]]

  val setIntUnion: Set[Int] = intMonoid.combine(Set(1, 2, 3), Set(3, 4, 5))
  println(s"SetIntUnion: ${setIntUnion}")

  val setStrUnion: Set[String] = stringMonoid.combine(Set("A", "B"), Set("B", "C"))
  println(s"SetStrUnion: ${setStrUnion}")
}
