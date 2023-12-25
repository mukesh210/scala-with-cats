package introduction


object JsonClient extends App {
  val bob = Person("Bob", "bob@gmail.com")

  // "Interface object"
  import introduction.JsonWriterInstances._
  println(Json.toJson(bob))

  // "interface syntax"
  import JsonSyntax._
  println(bob.toJson)

  // use implicitly to summon any value from implicit scope
  println(implicitly[JsonWriter[Person]])

  /*
  Json.toJson(Option("A String"))
    Recursive Implicit resolution:
      Compiler will search for implicit val of type "JsonWriter[Option[String]]"
      Since, there is no such implicit val. It will look for def and will eventually find
      "optionWriter".
      Compiler will then resolve implicit for "JsonWriter[String]". This will compiler is able to
      do RECURSIVE IMPLICIT RESOLUTION
   */
  Json.toJson(Option("A String"))
  Option("B String").toJson
}
