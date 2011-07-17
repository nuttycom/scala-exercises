trait Monad[M[_]] {
  def pure[A](a: A): M[A]
  def bind[A, B](m: M[A], f: A => M[B]): M[B]
}

class Reader[A] extends Monad[({type M[B] = Function1[A, B]})#M]

// vim: set ts=4 sw=4 et:
