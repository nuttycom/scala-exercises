trait Monster

class Twin(name: String, t: => Twin) extends Monster {
  lazy val twin = t
}

object Twins {
  val left:  Twin = new Twin("Left", right)
  lazy val right: Twin = new Twin("Right", left)
    
  def twins: (Twin, Twin) = (left, right)
}

object Main {
  def main(argv: Array[String]) = {
    val (left, right) = Twins.twins
    println(left, left.twin)
    println(right, right.twin)
  }
}

// vim: set ts=4 sw=4 et:
