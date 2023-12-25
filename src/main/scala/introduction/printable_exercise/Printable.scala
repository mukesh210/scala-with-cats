package introduction.printable_exercise

trait Printable[A] {
  def format(value: A): String
}

// interface which outside world can use to "format" and "print"
// also known as "Interface Objects"
object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  def print[A](value: A)(implicit p: Printable[A]): Unit = println(p.format(value))
}

object PrintableInstances {

  implicit val printableString: Printable[String] = new Printable[String] {
    override def format(value: String): String = value
  }

  implicit val printableInt: Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

  implicit val printableCat: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = {
      val name = Printable.format(value.name)
      val age = Printable.format(value.age)
      val color = Printable.format(value.color)
      s"${name} is a ${age} year-old ${color} cat"
    }
  }
}

// Type class + enrichment(using implicit classes)
object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)

    def print(implicit p: Printable[A]): Unit = println(p.format(value))
  }
}
