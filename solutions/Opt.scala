sealed trait Opt[+A] {
  def cata[B](b: B, f: A => B): B

  def getOrElse(a: A): A = cata(a, a => a)
  def map[B](f: A => B): Opt[B] = cata(none[B], a => some(f(a)))
  def flatMap[B](f: A => Opt[B]): Opt[B] = cata(none[B], f)
  def isEmpty: Boolean = cata(true, a => false)
  def filter(f: A => Boolean): Opt[A] = cata(none[B], a => if (f(a)) this else none[B])
  def orElse(o: Opt[A]): Opt[A] 
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
