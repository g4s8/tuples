package wtf.g4s8.tuples;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Pair. Tuple with 2 items.
 * @since 1.0
 */
public interface Pair<T, U> {

    /**
     * Apply pair using function.
     * @param <R> Result type
     * @param func Function to apply
     * @return Function result
     */
    <R> R apply(BiFunction<? super T, ? super U, ? extends R> func);

    /**
     * Accept pair items using consumer.
     * @param consumer Acceptor.
     */
    default void accept(BiConsumer<? super T, ? super U> consumer) {
        this.apply((first, second) -> {
            consumer.accept(first, second);
            return Tuple.None.VALUE;
        });
    }

    /**
     * Push third item to make a triplet.
     * @param <V> Third item type
     * @param val Third item value
     * @return Triplet of pair items and third item
     */
    default <V> Triplet<T, U, V> push(V val) {
        return this.apply((first, second) -> Triplet.of(first, second, val));
    }

    /**
     * Skip second value to make a unit.
     * @return Unit from first item
     */
    default Unit<T> pop() {
        return this.apply((first, second) -> Unit.of(first));
    }

    /**
     * New pair.
     * @param <T> First item type
     * @param <U> Second item type
     * @param first First item value
     * @param second Second item value
     * @return Pair
     */
    static <T, U> Pair<T, U> of(final T first, final U second) {
        return new Tuple.PairTuple<>(first, second);
    }

    /**
     * Zip two iterables into single iterable of pairs.
     * @param <T> First iterable type
     * @param <U> Second iterable type
     * @param first Iterable
     * @param second Iterable
     * @return Iterable
     */
    static <T, U> Iterable<Pair<T, U>> zip(final Iterable<? extends T> first, final Iterable<? extends U> second) {
        return new PairZip<>(first, second);
    }
}