package no.java.submitit.app.pages

import org.apache.wicket.markup.html.basic._
import org.apache.wicket.markup.html.list._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.link._
import model._
import widgets._
import common.Implicits._
import app.State

class ReviewPage extends LayoutPage {
  
  val p = State.get.currentPresentation
  add(new Label("title", p.title))
  add(new WikiMarkupText("abstract", p.abstr))

  add(new Label("language", p.language.toString))
  add(new Label("level", p.level.toString))
  add(new Label("duration", p.duration.toString))
  add(new WikiMarkupText("equipment", p.equipment))
  add(new WikiMarkupText("requiredExperience", p.requiredExperience))
  add(new WikiMarkupText("expectedAudience", p.expectedAudience))
  
  add(new ListView("speakers", p.speakers) {
    override def populateItem(item: ListItem) {
      val speaker = item.getModelObject.asInstanceOf[Speaker]
      item.add(new Label("name", speaker.name))
      item.add(new Label("email", speaker.email))
      item.add(new Label("bio", speaker.bio))
    }
  })
  
  add(new PageLink("submitLink", classOf[ConfirmPage]))
  add(new PageLink("editLink", classOf[SubmitPage]))
  
}