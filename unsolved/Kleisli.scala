trait Monad[M[_]] {
  def pure[A]: M[A]
  def bind[A, B](m: M[A], f: A => M[B]): M[B]
}

case class OptionT[F[_], A](v: F[Option[A]])

object OptionT {
  def OptionTM[F[_]](implicit m: Monad[F]): Monad[({type lam[a] = OptionT[F, a]})#lam] = error("todo")
}


case class Kleisli[A, F[_], B](v: A => F[B])

object Kleisli {
  def KleisliM[I, F[_]](implicit m: Monad[F]): Monad[({type lam[a] = Kleisli[I, F, a]})#lam] = error("todo")
}

