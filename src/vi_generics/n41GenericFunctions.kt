package vi_generics

import util.TODO
import java.util.*

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun <TYP, COLL : MutableCollection<TYP>> Collection<TYP>.partitionTo(collectionIn: COLL, emptyCollection: COLL, predicate: (TYP) -> Boolean ) : Pair<COLL, COLL> {
    this.forEach {
        val isPredicate = predicate.invoke(it)
        if (isPredicate) {
            collectionIn.add(it)
        } else {
            emptyCollection.add(it)
        }
    }
    return Pair(collectionIn, emptyCollection)
}

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
      return partitionTo(ArrayList(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
      return partitionTo(HashSet(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}