package wtf.g4s8.tuples;

import java.util.Iterator;

/**
 * ZIP iterable of triplets.
 * @since 1.0
 */
final class TripletZip<T, U, V> implements Iterable<Triplet<T, U, V>> {

    private final Iterable<? extends T> first;
    private final Iterable<? extends U> second;
    private final Iterable<? extends V> third;

    /**
     * @param first
     * @param second
     */
    public TripletZip(final Iterable<? extends T> first,
        final Iterable<? extends U> second,
        final Iterable<? extends V> third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    @Override
    public Iterator<Triplet<T, U, V>> iterator() {
        return new TripletIterator<>(this.first.iterator(), this.second.iterator(), this.third.iterator());
    }
    
    private static final class TripletIterator<T, U, V> implements Iterator<Triplet<T, U, V>> {

        private final Iterator<? extends T> first;
        private final Iterator<? extends U> second;
        private final Iterator<? extends V> third;
        
        /**
         * @param first
         * @param second
         */
        public TripletIterator(final Iterator<? extends T> first,
            final Iterator<? extends U> second,
            final Iterator<? extends V> third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public boolean hasNext() {
            return this.first.hasNext() && this.second.hasNext() && this.third.hasNext();
        }

        @Override
        public Triplet<T, U, V> next() {
            return Triplet.of(this.first.next(), this.second.next(), this.third.next());
        }

    }
}
