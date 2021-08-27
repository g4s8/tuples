package wtf.g4s8.tuples;

/**
 * Triplet. Tuple with 3 items.
 * @since 1.0
 */
public interface Triplet<T, U, V> {

    /**
     * Function that apply three values.
     */
    @FunctionalInterface
    public interface Function<T, U, V, R> {
        R apply(T first, U second, V third);
    }

    /**
     * Consumer that accept three values.
     */
    @FunctionalInterface
    public interface Consumer<T, U, V> {
        void accept(T first, U second, V third);
    }

    /**
     * Apply triple using function.
     * @param <R> Result type
     * @param func Function to apply
     * @return Function result
     */
    <R> R apply(Triplet.Function<? super T, ? super U, ? super V, ? extends R> func);

    /**
     * Accept values with consumer.
     * @param consumer Acceptor
     */
    default void accept(Triplet.Consumer<? super T, ? super U, ? super V> consumer) {
        this.apply((first, second, third) -> {
            consumer.accept(first, second, third);
            return Tuple.None.VALUE;
        });
    }

    /**
     * Skip third item to make a pair.
     * @return Pair from first and second items
     */
    default Pair<T, U> pop() {
        return this.apply((first, second, third) -> Pair.of(first, second));
    }

    /**
     * New triple.
     * @param <T> First item type
     * @param <U> Second item type
     * @param <V> Third item type
     * @param first First item value
     * @param second Second item value
     * @param third Third item value
     * @return Triplet of values provided
     */
    static <T, U, V> Triplet<T, U, V> of(final T first, final U second, final V third) {
        return new Tuple.TripletTuple<>(first, second, third);
    }

    /**
     * Zip three iterables into single iterable of triplets.
     * @param <T> First iterable type
     * @param <U> Second iterable type
     * @param <V> Third iterable type
     * @param first Iterable
     * @param second Iterable
     * @param third Iterable
     * @return Iterable
     */
    static <T, U, V> Iterable<Triplet<T, U, V>> zip(final Iterable<? extends T> first,
        final Iterable<? extends U> second, final Iterable<? extends V> third) {
        return new TripletZip<>(first, second, third);
    }
}