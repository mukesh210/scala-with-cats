package introduction

// Define a very simple JSON AST
sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json

final case class JsString(get: String) extends Json

final case class JsNumber(get: Double) extends Json

case object JsNull extends Json

// "interface objects" technique
object Json {
  def toJson[A](value: A)(implicit writer: JsonWriter[A]): Json = writer.write(value)
}

/*
  We can use "extension method" to extend existing type with interface methods
  This pattern is referred as "syntax" in cats
 */
object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit writer: JsonWriter[A]): Json = writer.write(value)
  }
}

// The "serialize to JSON" behaviour is encoded in this trait
trait JsonWriter[A] {
  def write(value: A): Json
}

// type class instances are concrete implementation of type class with implicit
object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }

  implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
    override def write(value: Person): Json = JsObject(Map(
      "name" -> JsString(value.name),
      "email" -> JsString(value.email)
    ))
  }

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = {
    new JsonWriter[Option[A]] {
      override def write(value: Option[A]): Json = {
        value match {
          case Some(value) => writer.write(value)
          case None => JsNull
        }
      }
    }
  }
}