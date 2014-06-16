---
layout: post
title: Exceptions Are Bad
comments: true
---

> Exceptions are the primary error-handling mechanism employed by many widely-used languages. 
> They are also a side-effect that makes a liar of the type system, and makes local reasoning 
> about code far more difficult. They represent an undeclared method result smuggled through 
> a back-channel separate from its declared return type. Furthermore, they transitively become 
> an undeclared result of anything that calls that method, and anything that calls that, and so 
> on. Trying to reason about the correct behaviour of code becomes very difficult, since the 
> return type can no longer give you enough information. Exceptions kill modularity and inhibit 
> composition.

Source: [The abject failure of weak typing][abject]

Go uses [multiple return values][multiple] to propogate errors. In Scala you have [Option][option], 
[Either][either], or [Validation][validation] to provide more type safe error handling.

[abject]: http://techblog.realestate.com.au/the-abject-failure-of-weak-typing/
[multiple]: http://blog.golang.org/error-handling-and-go
[option]: http://www.scala-lang.org/api/current/#scala.Option
[either]: http://www.scala-lang.org/api/current/#scala.util.Either
[validation]: http://blog.lunatech.com/2012/03/02/validation-scala
