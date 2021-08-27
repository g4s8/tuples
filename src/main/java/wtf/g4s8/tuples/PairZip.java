package wtf.g4s8.tuples;

import java.util.Iterator;

/**
 * ZIP iterable of pairs.
 * @since 1.0
 */
final class PairZip<T, U> implements Iterable<Pair<T, U>> {

    private final Iterable<? extends T> first;

    private final Iterable<? extends U> second;
    /**
     * @param first
     * @param second
     */
    public PairZip(Iterable<? extends T> first, Iterable<? extends U> second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public Iterator<Pair<T, U>> iterator() {
        return new ZipIterator<>(this.first.iterator(), this.second.iterator());
    }
    
    private static final class ZipIterator<T, U> implements Iterator<Pair<T, U>> {

        private final Iterator<? extends T> first;
        private final Iterator<? extends U> second;
        
        /**
         * @param first
         * @param second
         */
        public ZipIterator(Iterator<? extends T> first, Iterator<? extends U> second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean hasNext() {
            return this.first.hasNext() && this.second.hasNext();
        }

        @Override
        public Pair<T, U> next() {
            return Pair.of(this.first.next(), this.second.next());
        }

    }
}
