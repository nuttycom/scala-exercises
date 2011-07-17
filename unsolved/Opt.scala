sealed trait Opt[+A] {
  def cata[B](b: B, f: A => B): B

  // all the rest of these methods should be implemented solely in terms of cata
  def getOrElse(a: A): A = error("todo")
  def map[B](f: A => B): Opt[B] = error("todo")
  def flatMap[B](f: A => Opt[B]): Opt[B] = error("todo")
  def isEmpty: Boolean = error("todo")
  def filter(f: A => Boolean): Opt[A] = error("todo")
  def orElse(o: Opt[A]): Opt[A] = error("todo")
}

object Opt {
  def some[A](a: A): Opt[A] = new Opt[A] {
    def cata[B](b: B, f: A => B): B = f(a)
  }

  def none[A]: Opt[A] = new Opt[A] {
    def cata[B](b: B, f: A => B): B = b
  }
}



// vim: set ts=4 sw=4 et:
