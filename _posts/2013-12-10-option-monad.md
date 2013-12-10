---
layout: post
title: Scala's Option Monad
cateogyr: posts
---

Recently I've been playing with Monads and making an effort to
understand how they work and their usefulness in the greater context
of writing good software. A common example given is the Maybe monad in
Haskell. I believe the same concept is called [Option][option] in
Scala.

Commonly I would use Option as follows:

{% highlight scala %}

val hash = HashMap("one" -> 1, "two" -> 2)
val one = hash.get("one") match {
	case Some(n) => n // this case is used
	case None => 0
}

val three = hash.get("three") match {
	case Some(n) => n
    case None => 0 // this case is used
}

{% endhighlight %}

This works, but doesn't leverage the fact that Option is a
Monad. Since it's a Monad, we can easily extract the value and perform
simple compositions on the Option object.

{% highlight scala %}

val hash = HashMap("one" -> 1, "two" -> 2)

// simply grab the value
hash.get("one") getOrElse 0 // 1

// or we can perform operations on it inside the Option Monad
hash.get("one").map(_*10).filter(_ <= 10).map(_ / 3) // Some(3)
hash.get("one").map(_*20).filter(_ <= 10).map(_ / 3) // None, filter(_ <= 10) results in None
hash.get("three").map(_*10).filter(_ <= 10).map(_ / 3) // None, "three" doesn't exist

{% endhighlight %}

According to the Scala documentation, this is the more idiomatic
approach when dealing with Option.

[option]: http://www.scala-lang.org/api/2.10.3/#scala.Option
