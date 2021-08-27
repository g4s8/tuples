package wtf.g4s8.tuples.hm;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import wtf.g4s8.tuples.Triplet;

/**
 * Hamcrest matcher for @{link Triplet}.
 * @since 1.0
 */
public final class TripletMatcher<T, V, U> extends TypeSafeMatcher<Triplet<T, V, U>> {

    private final Matcher<? super T> first;
    private final Matcher<? super V> second;
    private final Matcher<? super U> third;

    public TripletMatcher(final T first, V second, U third) {
        this(Matchers.equalTo(first), Matchers.equalTo(second), Matchers.equalTo(third));
    }

    public TripletMatcher(final Matcher<? super T> first, final Matcher<? super V> second,
        final Matcher<? super U> third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("triplet has first item ").appendDescriptionOf(this.first)
            .appendText("and secod item ").appendDescriptionOf(this.second)
            .appendText("and third item ").appendDescriptionOf(this.third);
    }

    @Override
    protected boolean matchesSafely(Triplet<T, V, U> item) {
        return item.apply((one, two, three) -> this.first.matches(one) && this.second.matches(two) && this.third.matches(three));
    }

    @Override
    protected void describeMismatchSafely(final Triplet<T, V, U> item, final Description description) {
        item.accept((one, two, three) -> description.appendText("pair first item was ").appendValue(one)
            .appendText(" and second item was ").appendValue(two)
            .appendText(" and third item was ").appendValue(three));
    }
}
