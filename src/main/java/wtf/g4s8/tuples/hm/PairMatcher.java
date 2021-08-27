package wtf.g4s8.tuples.hm;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import wtf.g4s8.tuples.Pair;

/**
 * Hamcrest matcher for {@link Pair}.
 * @since 1.0
 */
public final class PairMatcher<T, V> extends TypeSafeMatcher<Pair<T, V>> {

    private final Matcher<? super T> first;
    private final Matcher<? super V> second;

    public PairMatcher(final T first, final V second) {
        this(Matchers.equalTo(first), Matchers.equalTo(second));
    }
    
    public PairMatcher(final Matcher<? super T> first, final Matcher<? super V> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("pair has first item ").appendDescriptionOf(this.first)
            .appendText("and secod item ").appendDescriptionOf(this.second);
    }

    @Override
    protected boolean matchesSafely(Pair<T, V> item) {
        return item.apply((one, two) -> this.first.matches(one) && this.second.matches(two));
    }

    @Override
    protected void describeMismatchSafely(final Pair<T, V> item, final Description description) {
        item.accept((one, two) -> description.appendText("pair first item was ").appendValue(one).appendText(" and second item was ").appendValue(two));
    }
}
