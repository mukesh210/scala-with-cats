package introduction.printable_exercise

object PrintableClient extends App {
  val cat = Cat("alpha-x", 2, "Red")

  // using implicit resolution for Printable[Cat]
  import PrintableInstances._
  println(Printable.format(cat))

  // using type class + enrichment... much easier to use and available for any datatype
  // for which compiler is able to resolve Printable[...]
  import PrintableSyntax._
  println(cat.format)
}
