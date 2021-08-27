package wtf.g4s8.tuples;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Standard implementation of {@link Unit}, {@link Pair} and {@link Triplet}.
 * @since 1.0
 */
abstract class Tuple<T, U, V> implements Serializable {

    private static final long SerialVersionUID = 1974123789616905123l;

    /**
     * Empty type value. Is not accessible from outside of this package.
     */
    enum None { VALUE }

    /**
     * First value.
     */
    private final T first;

    /**
     * Second value.
     */
    private final U second;

    /**
     * Third value.
     */
    private final V third;

    /**
     * @param first
     */
    private Tuple(T first, final U second, V third) {
        this.first = Objects.requireNonNull(first, "first is null");
        this.second = Objects.requireNonNull(second, "second is null");
        this.third = Objects.requireNonNull(third, "third is null");
    }

    public final <R> R apply(Function<? super T, ? extends R> func) {
        return func.apply(this.first);
    }

    public final void accept(Consumer<? super T> consumer) {
        consumer.accept(this.first);
    }

    public final <R> R apply(BiFunction<? super T, ? super U, ? extends R> func) {
        return func.apply(this.first, this.second);
    }

    public final void accept(BiConsumer<? super T, ? super U> consumer) {
        consumer.accept(this.first, this.second);
    }

    public final <R> R apply(final Triplet.Function<? super T, ? super U, ? super V, ? extends R> func) {
        return func.apply(first, second, third);
    }

    public final void accept(final Triplet.Consumer<? super T, ? super U, ? super V> consumer) {
        consumer.accept(first, second, third);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Tuple)) {
            return false;
        }
        @SuppressWarnings("rawtypes") final Tuple other = (Tuple) obj;
        return Objects.equals(this.first, other.first)
            && Objects.equals(this.second, other.second)
            && Objects.equals(this.third, other.third);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.first, this.second, this.third);
    }

    @Override
    public final String toString() {
        return String.format(
            "<%s>",
            Stream.of(this.first, this.second, this.third)
                .filter(x -> x != None.VALUE)
                .map(Object::toString)
                .collect(Collectors.joining(", "))
            );
    }

    /**
     * Tuple implementation for {@link Unit}.
     */
    static final class UnitTuple<T> extends Tuple<T, None, None> implements Unit<T> {

        UnitTuple(final T val) {
            super(val, None.VALUE, None.VALUE);
        }

        @Override
        public <U> Pair<T, U> push(final U val) {
            return new PairTuple<>(super.first, val);
        }
    }

    /**
     * Tuple implementation for {@link Pair}.
     */
    static final class PairTuple<T, U> extends Tuple<T, U, None> implements Pair<T, U> {

        PairTuple(final T first, final U second) {
            super(first, second, None.VALUE);
        }

        @Override
        public <V> Triplet<T, U, V> push(V val) {
            return new TripletTuple<>(super.first, super.second, val);
        }

        @Override
        public Unit<T> pop() {
            return new UnitTuple<>(super.first);
        }
    }

    /**
     * Tuple implementation for {@link Triplet}.
     */
    static final class TripletTuple<T, U, V> extends Tuple<T, U, V> implements Triplet<T, U, V> {

        public TripletTuple(final T first, final U second, final V third) {
            super(first, second, third);
        }

        @Override
        public Pair<T, U> pop() {
           return new PairTuple<>(super.first, super.second);
        }
    }
}
