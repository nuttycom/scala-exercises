trait Applic[F[_]] {
  def applic[A, B](f: F[A => B]): F[A] => F[B]
}

object OptionApplic extends Applic[Option] {
  def applic[A, B](f: Option[A => B]): Option[A] => Option[B] = error("todo")
}

object ListApplicZip extends Applic[List] {
  // implement the applicative by zipping the lists
  def applic[A, B](fs: List[A => B]): List[A] => List[B] = error("todo")
}

object ListApplic extends Applic[List] {
  //implement the applicative by flatmapping the lists
  def applic[A, B](fs: List[A => B]): List[A] => List[B] = error("todo")
}

// vim: set ts=4 sw=4 et:
