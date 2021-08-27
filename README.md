Object oriented Java tuples.

 - No data accessors
 - No ugly class or method names ~`pair._1()`~, ~`new Tuple3()`~
 - All tuple types are interfaces
 - Strong encapsulation of tuples data
 - Standard tuple implementations are immutable
 - Built-in support of `equals`, `hashCode`, `toString`, `Serializable` in standard implementation

[![CI checks](https://github.com/g4s8/tuples/actions/workflows/ci-checks.yml/badge.svg)](https://github.com/g4s8/tuples/actions/workflows/ci-checks.yml)
[![Maven Central](https://img.shields.io/maven-central/v/wtf.g4s8/tuples)](https://maven-badges.herokuapp.com/maven-central/wtf.g4s8/tuples)

### Usage

Add dependency
```xml
<dependency>
    <groupId>wtf.g4s8</groupId>
    <artifactId>tuples</artifactId>
</dependency>
```

Using in code:
```java
// creating new tuples
var pair = Pair.of(1, "b"); // <1, "b">
var triplet = Triplet.of(4, false, 0.2); // <4, false, 0.2>

// applying tuple data
String result = pair.apply((i, s) -> String.format("%s - %d", s, i)); // b - 1

// accepting tuple data
triplet.accept((i, b, d) -> System.out.printf("%d - (%b) %f\n", i, b, d)); // 4 - (false) 0.2 

// push and pop
Unit<Integer> iUnit = Unit.of(1); // <1>
Pair<Integer, String> pair = unit.push("a"); // <1, "a">
Unit<String> sUnit = pair.apply((i, s) -> Pair.of(s, i)).pop(); // <"a">
Triplet<String, Double, Boolean> triplet = sUnit.push(1.1).push(true); // <"a", 1.1, true>

// zipping iterables
var zip = Pair.zip(Arrays.asList(1, 2, 3), Arrays.asList("a", "b", "c")); // [<1, "a">, <2, "b">, <3, "c">]
```

### Design

Each tuple object specify the behavior and encapsulates the data.
Tuple primitives are interfaces with just a minimal required methods.
Data is strongly encapsulated and can not be accessed directly.
All types are immutable.
The only was yo access the data is to call `accept` or `apply` methods.

There are three objects exist:
 - `Unit<T>` - singleton tuple
 - `Pair<T, U>` - pair of two value
 - `Triplet<T, U, V>` - three values

### API

Each tuple has two primary methods:
 - `apply(function)` - apply function to tuple data, return function result
 - `accept(consumer)` - consume tuple data, return nothing

And additional methods to add or remove data:
 - `push(item)` - change tuple type by adding new element (unit -> pair -> triplet)
 - `pop()` - skip last element, reduce tuple size (triplet -> pair -> unit)

### Constructing

Each tuple type has factory method `of(...)`:
 - `Unit.of(T) -> Unit<T>`
 - `Pair.of(T, U) -> Pair<T, U>`
 - `Triplet.of(T, U, V) -> Triplet<T, U, V>`

### ZIP

Also, there are `zip` methods available to zip two or more lists:
 - `Pair.zip(Iterable<T>, Iterable<U>) -> Iterable<Pair<T, U>>`
 - `Triplet.zip(Iterable<T>, Iterable<U>, Iterable<V>) -> Iterable<Triplet<T, U, V>>`

### Optional

Unit tuple has `maybe` method to convert from optional:
 - `Unit.maybe(Optional<T>) -> Optional<Unit<T>>`

### Extras

All standard tuple classes created from `of` factory method
implements `equals`, `hashCode` and `toString` methods.

### Testing

Each tuple type has Hamcrest matcher in `wtf.g4s8.tuples.hm` package
(to use it, add `org.hamcrest:hamcrest` Maven dependency):
 - `UnitMatcher<T>` for `Unit<T>`
 - `PairMatcher<T, U>` for `Pair<T, U>`
 - `TripletMatcher<T, U, V>` for `Triplet<T, U, V>`
