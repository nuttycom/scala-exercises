package lqueue

sealed trait List[+T]

case class Cons[+T](head: T, tail: List[T]) extends List[T]
case object Nil extends List[Nothing]

object List {
  def head[T](l: List[T]): Option[T] = l match {
    case Cons(v, tail) => Some(v)
    case Nil => None
  }

  def map[T, U](l: List[T], f: T => U): List[U] = l match {
    case Cons(v, tail) => Cons(f(v), map(tail, f))
    case Nil => Nil
  }

  def cata[T, U](l: List[T], init: U)(f: (T, U) => U): U = l match {
    case Cons(v, tail) => cata(tail, f(v, init))(f)
    case Nil => init
  }

  def reverse[T](l: List[T]): List[T] = cata[T, List[T]](l, Nil) {
    (t, appendTo) => Cons(t, appendTo)
  }
}

case class Queue[+T](in: List[T], out: List[T])

object Queue {
  import List._

  def enqueue[T](q: Queue[T], t: T): Queue[T] = Queue(Cons(t, q.in), q.out)

  def dequeue[T](q: Queue[T]): (Queue[T], Option[T]) = q.out match {
    case Cons(v, tail) => (Queue[T](q.in, tail), Some(v))
    case Nil => reverse(q.in) match {
      case Cons(v, tail) => (Queue[T](Nil, tail), Some(v))
      case Nil => (q, None)
    }
  }
}



// vim: set ts=4 sw=4 et:
