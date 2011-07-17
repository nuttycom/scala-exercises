trait Applic[F[_]] {
  def applic[A, B](f: F[A => B]): F[A] => F[B]
}

object OptionApplic extends Applic[Option] {
  def applic[A, B](f: Option[A => B]): Option[A] => Option[B] = {
    (opt: Option[A]) => f.flatMap(opt.map)
  }
}

object ListApplicZip extends Applic[List] {
  def applic[A, B](fs: List[A => B]): List[A] => List[B] = {
    (as: List[A]) => (fs zip as) map {
      case (f, a) => f(a)
    }
  }
}

object ListApplic extends Applic[List] {
  def applic[A, B](fs: List[A => B]): List[A] => List[B] = {
    (as: List[A]) => for (f <- fs; a <- as) yield f(a)
  }
}

// vim: set ts=4 sw=4 et:
