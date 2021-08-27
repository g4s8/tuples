package wtf.g4s8.tuples;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Unit. Tuple with one item.
 * @since 1.0
 */
public interface Unit<T> {
    
    /**
     * Apply unit using function.
     * @param <R> Result type
     * @param func Function to apply
     * @return Function result
     */
    <R> R apply(Function<? super T, ? extends R> func);

    /**
     * Accept unit with consumer.
     * @param consumer Acceptor
     */
    default void accept(Consumer<? super T> consumer) {
        this.apply(first -> {
            consumer.accept(first);
            return Tuple.None.VALUE;
        });
    }

    /**
     * Push second item to make a pair.
     * @param <U> Second item type
     * @param val Second item value
     * @return Pair of unit and second item
     */
    default <U> Pair<T, U> push(final U val) {
        return this.apply(first -> Pair.of(first, val));
    }

    /**
     * New unit.
     * @param <T> Item type
     * @param val Item value
     * @return Unit
     */
    static <T> Unit<T> of(T val) {
        return new Tuple.UnitTuple<>(val);
    }

    static <T> Optional<Unit<? extends T>> maybe(final Optional<? extends T> optional) {
        return optional.map(Unit::of);
    }
}
