package lqueue

sealed trait List[+A]

case class Cons[+A](head: A, tail: List[A]) extends List[A]
case object Nil extends List[Nothing]

object List {
  def head[A](l: List[A]): Option[A] = error("todo")

  def map[A, B](l: List[A], f: A => B): List[B] = error("todo")

  def cata[A, B](l: List[A], init: B)(f: (A, B) => B): B = error("todo")

  def reverse[A](l: List[A]): List[A] = error("todo")
}

case class Queue[+A](in: List[A], out: List[A])

object Queue {
  import List._

  def enqueue[A](q: Queue[A], t: A): Queue[A] = error("todo")

  def dequeue[A](q: Queue[A]): (Queue[A], Option[A]) = error("todo")
}



// vim: set ts=4 sw=4 et:
