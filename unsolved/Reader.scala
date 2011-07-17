trait Monad[M[_]] {
  def pure[A](a: A): M[A]
  def bind[A, B](m: M[A], f: A => M[B]): M[B]
}

object Reader {
  def ReaderM[I]: Monad[({type M[a] = Function1[I, a]})#M] = new Monad[({type M[a] = Function1[I, a]})#M] {
    override def pure[A](a: A): Function1[I, A] = error("todo")
    override def bind[A, B](f1: Function1[I, A], f2: A => Function1[I, B]): Function1[I, B] = error("todo")
  }
}

// vim: set ts=4 sw=4 et:
