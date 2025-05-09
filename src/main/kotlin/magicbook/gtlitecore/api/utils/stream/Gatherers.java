package magicbook.gtlitecore.api.utils.stream;

import java.util.Arrays;
import java.util.List;;

/**
 * Implementations of {@link Gatherer} that provide useful intermediate operations, such as
 * windowing functions, folding functions, transforming elements concurrently, e.t.c.
 */
public final class Gatherers
{

    /**
     * Returns a {@code Gatherer} that gathers elements into windows -- encounter-ordered
     * groups of elements -- of a fixed size. If the stream is empty then no window will
     * be produced. The last window may contain fewer elements than the supplied window
     * size. For example:
     * <pre>{@code
     *     // Will contains: [[1, 2, 3], [4, 5, 6], [7, 8]]
     *     List<List<Integer>> windows = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
     *     LazyStreams.gatherer(windows, Gatherers.windowFixed(3)).toList();
     * }</pre>
     *
     * @param windowSize The size of the windows.
     * @return           A new gatherer which groups elements into fixed-size windows.
     *
     * @param <TR> The type of elements the returned gatherer consumes and the contents
     *             of the windows it produces.
     *
     * @throws IllegalArgumentException When {@code windowSize} is less than 1.
     *
     * @apiNote For efficiency reasons, windows may be allocated contiguously and eagerly.
     *          This means that choosing large window sizes for small streams may use excessive
     *          memory for the duration of evaluation of this operation.
     *
     * @implSpec Each window produced is an unmodifiable List; calls to any mutator method
     *           will always cause {@link UnsupportedOperationException} to be thrown. There
     *           are no guarantees on the implementation type or serializability of the
     *           produced lists.
     */
    @SuppressWarnings("unchecked")
    public static <TR> Gatherer<TR, ?, List<TR>> windowFixed(int windowSize)
    {
        if (windowSize < 1)
            throw new IllegalArgumentException("The 'windowSize' must be greater than zero");

        class FixedWindow
        {
            Object[] window;
            int at;

            FixedWindow()
            {
                at = 0;
                window = new Object[windowSize];
            }

            boolean integrate(TR element, Gatherer.Downstream<? super List<TR>> downstream)
            {
                window[at++] = element;
                if (at < windowSize)
                {
                    return true;
                }
                else
                {
                    final Object[] oldWindow = window;
                    window = new Object[windowSize];
                    at = 0;
                    return downstream.push((List<TR>) Arrays.asList(oldWindow));
                }
            }

            void finish(Gatherer.Downstream<? super List<TR>> downstream)
            {
                if (at > 0 && !downstream.isRejecting())
                {
                    Object[] lastWindow = new Object[at];
                    System.arraycopy(window, 0, lastWindow, 0, at);
                    window = null;
                    at = 0;
                    downstream.push((List<TR>) Arrays.asList(lastWindow));
                }
            }

        }

        return Gatherer.<TR, FixedWindow, List<TR>>ofSequential(FixedWindow::new, // Initializer.
                Gatherer.Integrator.<FixedWindow, TR, List<TR>>ofGreedy(FixedWindow::integrate), // Integrator.
                FixedWindow::finish); // Finisher.
    }

}
