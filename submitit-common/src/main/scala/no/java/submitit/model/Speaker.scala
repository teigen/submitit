package no.java.submitit.model

class Speaker {
  
  var personId: String = _
  var name: String = _
  var email: String = _
  var bio: String = _
  var picture: Picture = _
  
  override def toString =
    "name: " + name +
    " email: " + email +
    " bio: " + bio

  override def equals(other: Any): Boolean = {
    other match {
      case that: Speaker =>
        (that canEqual this) && 
          personId == that.personId && 
          name == that.name && 
          bio == that.bio
      case _ => false
    }
  }
  
  def canEqual(other: Any): Boolean = {
    other.isInstanceOf[Speaker]
  }
  
  override def hashCode(): Int =
    name.hashCode + 41 * bio.hashCode
  
}

object Speaker {
  
  def apply(name: String, email: String, bio: String, picture: Picture): Speaker = {
    val s = new Speaker()
    s.name = name
    s.email = email
    s.bio = bio
    s.picture = picture
    s
  }
}