// case class OptionTrans[F[_], A](v: F[Option[A]])
// case class Function1T[A, F[_], B](v: A => F[B])
// def OptionTransMonad[F[_]: Monad]: Monad[({type lam[a] = OptionT[F, a]}#lam] = ...
// def Function1TransMonad[A, F, [_]: Monad]: Monad[({type lam[a] = Function1Trans[A, F, a]}#lam] = 

trait Monad[M[_]] {
  def pure[A]: M[A]
  def bind[A, B](m: M[A], f: A => M[B]): M[B]
}

case class OptionT[F[_], A](v: F[Option[A]])

object OptionT {
  def OptionTM[F[_]](implicit m: Monad[F]): Monad[({type lam[a] = OptionT[F, a]})#lam] = new Monad[({type lam[a] = OptionT[F, a]})#lam] {
    override def pure[A]: OptionT[F, A] = OptionT(m.pure[Option[A]])
    override def bind[A, B](o: OptionT[F, A], f: A => OptionT[F, B]): OptionT[F, B] = OptionT(
      m.bind(o.v, (a: Option[A]) => a.map(f).getOrElse(pure[B]).v)
    )
    
  }
}


case class Kleisli[A, F[_], B](v: A => F[B])

object Kleisli {
  def KleisliM[I, F[_]](implicit m: Monad[F]): Monad[({type lam[a] = Kleisli[I, F, a]})#lam] = new Monad[({type lam[a] = Kleisli[I, F, a]})#lam] {
    override def pure[A]: Kleisli[I, F, A] = Kleisli((i: I) => m.pure[A])
    override def bind[A, B](k: Kleisli[I, F, A], f: A => Kleisli[I, F, B]): Kleisli[I, F, B] = Kleisli( 
      (i: I) => m.bind(k.v(i), (a: A) => f(a).v(i))
    )
  }
}

