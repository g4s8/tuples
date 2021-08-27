package wtf.g4s8.tuples.hm;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import wtf.g4s8.tuples.Unit;

/**
 * Hamcrest matcher for {@link Unit}.
 * @since 1.0
 */
public final class UnitMatcher<T> extends TypeSafeMatcher<Unit<T>> {

    private final Matcher<? super T> matcher;

    public UnitMatcher(final T val) {
        this(Matchers.equalTo(val));
    }
    
    public UnitMatcher(final Matcher<? super T> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("has item ").appendDescriptionOf(this.matcher);
    }

    @Override
    protected boolean matchesSafely(Unit<T> item) {
        return this.matcher.matches(item.apply(x -> x));
    }

    @Override
    protected void describeMismatchSafely(final Unit<T> item, final Description description) {
        item.accept(x -> description.appendText("item was ").appendValue(x));
    }
}
