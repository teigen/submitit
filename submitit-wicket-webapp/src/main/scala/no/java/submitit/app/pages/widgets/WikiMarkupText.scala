package no.java.submitit.app.pages.widgets

import org.apache.wicket.markup.html.basic._
import org.apache.wicket.model._
import org.apache.wicket.util.string.Strings.escapeMarkup
import scala.util.matching._

class WikiMarkupText (id: String, label: String) extends Label(id) {
  
  setEscapeModelStrings(false)
  val escaped = if (label != null) createMarkup(label) else null
  println(escaped)
  
  private def createMarkup(string: String) = applyRegexes(escapeMarkup(string).toString, WikiMarkupText.regexes)
  
  private def applyRegexes(value: String, holders: List[(Regex, String)]): String = 
    holders match {
      case (regex, replacement) :: xs => applyRegexes(regex.replaceAllIn(value, replacement), xs)
      case Nil => value
    }
  
  setModel(new Model(escaped))
  
}

private object WikiMarkupText {
  
  val newLines = ("""\n""".r, "<br />")
  val bold = ("""\*([^*]*)\*""".r, """<b>$1</b>""")
  val italics = ("""_([^_]*)_""".r, """<i>$1</i>""")
  val regexes = List(newLines, bold, italics)
}
