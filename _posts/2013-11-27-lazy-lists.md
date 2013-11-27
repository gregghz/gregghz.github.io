---
layout: post
title: Lazy Lists and Android
comments: true
---

Chet Haase has an article over at [CodeDependent][] called
[Lazy Lists][]. I think the main point of the article is to illustrate
a simple technique to avoid unnecessary memory allocation in a
constrained environment like Android. He gives a solution (in Java,
naturally) showing how to lazily instantiate a couple of lists only
once the lists are actually needed. It's simple enough. If the list
we're adding to is null, instantiate it and add the item, otherwise,
just add the item.

There are two oddities in his implementation I'd like to point out,
and then mostly ignore. He checks for uniqueness when adding an item
to a list. Chet does mention this as peculiar to his situation but
doesn't elaborate. In generel this makes a List seem like an odd
choice of data structures. A Set would probably be a much better
choice (I'll give an example later). He is also nulling out the list
once it reaches a length of zero. I can only guess that this is an
attempt to get the list GC'd more quickly once it's no longer
needed. However, I think in general, it's not a good idea to assume
that a list of length zero means the same thing as a list we don't
need anymore so I'm going to ignore this bit, but I do concede that in
specific cases this could be a fair assumption.

Overall, I think Chet's general idea is a good one. It's a very simple
instance of lazy evaluation. You don't allocate a new list until you
know you need that list. This saves a bit of memory and a few CPU
cycles. He basically abstracts out the details of this lazy
instantiation into two static methods so that the consumer of one of
these lazy lists doesn't ever have to worry about the details of how
it works.

More than anything, I think this pattern does more to highlight the
weaknesses of Java than anything else. A (very) little scala will do
the job for us just fine.

If we ignore the oddities I mentioned above (nulling out the empty
list by instead opting to reuse the already allocated list, and
checking for uniqueness when inserting into the list) the equivilent
scala code would look like this (I'm going to stick to using Java's
data structures for now, that's a Java List, not a Scala List):

{% highlight scala %}
class LazyLists {
    lazy val intList: List[Int] = new ArrayList[Int]()
    lazy val floatList: List[Double] = new ArrayList[Double]()
        
    def addItemBest(item: Int): Unit = intList.add(item)
    def addItemBest(item: Double): Unit = floatList.add(item)
    def removeItemBest(item: Int): Unit = intList.remove(item.asInstanceOf[Object])
    def removeItemBest(item: Double): Unit = floatList.remove(item)
}
{% endhighlight %}

That's it. We don't need a helper class (Chet's LazyListManager). And
we still don't have to think about when/where we are initializing
these lists. Scala's lazy keyword takes care of all the boilerplate
Java makes you work so hard to hide.

You can compare the Scala and Java implementations in a [gist][] I
created.

I think the biggest advantage here is that the lazy keyword can be
applied to any data. The original example would only work on
Lists. You would need a new helper class for each type of data you
wanted to lazily instantiate (or at least a sufficiently general
helper).

In Chet's post, he required each element to be unique. How can we
accomplish that? We could create a helper class, as Chet did, and send
all add and remove requests through that helper. But again, we'd end
up needing a helper for each type we want to lazily instantiate. As I
mentioned above, we can just use Sets (specifically a
[mutable.LinkedHashSet][] so we can maintain traversal order and the
mutation semantics in the original example):

{% highlight scala %}
class LazySets {
    lazy val intSet: Set[Int] = LinkedHashSet[Int]()
    lazy val floatSet: Set[Double] = LinkedHashSet[Double]()

    def add(item: Int): Unit = intSet.add(item)
    def add(item: Double): Unit = floatSet.add(item)
    def remove(item: Int): Unit = intSet.remove(item)
    def remove(item: Double): Unit = floatSet.remove(item)
}
{% endhighlight %}

Chet could have used a [java.util.LinkedHashSet][] in his example as
well. I'm not sure why he didn't. He only mentions his desire for
unique elements and then continues to use a List. My point in showing
the code above is only to illustrate that lazy is no respecter of
persons. It will work with any data structure. It will work with any
expression, in fact.

{% highlight scala %}
lazy val a = 12 + 15
lazy val b = someComplexFunction()
lazy val c = if (a * 100 - 15 > 99) True else False
{% endhighlight %}

Each of these will only evaluate the value on the right hand side when
the variable is accessed for the first time. It's as though they were all written like this:

{% highlight scala %}
val athunk = () => 12 + 15
var _a: Option[Int] = None
val a = () => match _a {
    case Some(a) => a
    case None => { _a = Some(athunk()); _a }
}
{% endhighlight %}

Just using the lazy keyword is much simpler.

Now you might be saying something like, "this is all great, but
Android uses Java, not Scala, so it's really useless for my Android
projects." Fortunately, Scala compiles to Java bytecode. The Android build
system turns bytecode into .dex files for Dalvik. That means it's not
too difficult to use Scala on Android. To help you out (and because
you really should stop using Java) I found a sample Android project
that utilizes Scala.

[jayway/maven-android-plugin-samples][]

In a later post I plan to talk about real lazy lists. That is, lists
whose elements are evaluated lazily. Look out for that. I think it's a
much more interesting topic than the lazy keyword itself.

[CodeDependent]: http://graphics-geek.blogspot.com/
[Lazy Lists]: http://graphics-geek.blogspot.com/2013/09/lazy-lists.html
[gist]: https://gist.github.com/gregghz/7677880
[mutable.LinkedHashSet]: http://www.scala-lang.org/api/current/index.html#scala.collection.mutable.LinkedHashSet
[java.util.LinkedHashSet]: https://developer.android.com/reference/java/util/LinkedHashSet.html
[jayway/maven-android-plugin-samples]: https://github.com/jayway/maven-android-plugin-samples/tree/master/scala
