package wtf.g4s8.tuples;

import java.util.Arrays;
import java.util.function.BiFunction;

import org.hamcrest.Matchers;

import wtf.g4s8.oot.ConsoleReport;
import wtf.g4s8.oot.FailingReport;
import wtf.g4s8.oot.SequentialTests;
import wtf.g4s8.oot.SimpleTest;
import wtf.g4s8.oot.TestCase;
import wtf.g4s8.tuples.hm.PairMatcher;
import wtf.g4s8.tuples.hm.TripletMatcher;
import wtf.g4s8.tuples.hm.UnitMatcher;

/**
 * Tests main class.
 * @since 1.0
 */
public final class MainTest extends TestCase.Wrap {
    
    private MainTest() {
        super(
            new SequentialTests(
                new SimpleTest<>("unit", Unit.of(1), new UnitMatcher<>(1)),
                new SimpleTest<>("pair", Pair.of(2, 3), new PairMatcher<>(2, 3)),
                new SimpleTest<>("triplet", Triplet.of(4, 5, 6), new TripletMatcher<>(4, 5, 6)),
                new SimpleTest<>(
                    "pair zip",
                    Pair.zip(Arrays.asList(1, 2, 3), Arrays.asList("a", "b", "c")),
                    Matchers.contains(
                        new PairMatcher<>(1, "a"),
                        new PairMatcher<>(2, "b"),
                        new PairMatcher<>(3, "c")
                    )
                ),
                new SimpleTest<>(
                    "triplet zip",
                    Triplet.zip(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList("a", "b", "c"),
                        Arrays.asList(true, false, true)
                    ),
                    Matchers.contains(
                        new TripletMatcher<>(1, "a", true),
                        new TripletMatcher<>(2, "b", false),
                        new TripletMatcher<>(3, "c", true)
                    )
                )
            )
        );
    }

    /**
     * Test entry point.
     */
    public static void test() throws Exception {
        try (FailingReport report = new FailingReport(new ConsoleReport())) {
            new MainTest().run(report);
        }
    }
}
