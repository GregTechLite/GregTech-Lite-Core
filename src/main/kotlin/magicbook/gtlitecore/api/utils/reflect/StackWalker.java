package magicbook.gtlitecore.api.utils.reflect;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A stack walker, i.e. a fast method to traversing or calling the stack.
 * <p>
 * The {@link #walk} method opens a sequential stream of {@code StackFrame}s for the current
 * thread and then applies the given function to walk the {@code StackFrame} stream. The stream
 * reports stack frame elements in order, from the top most frame that represents the execution
 * point at which the stack was generated to the bottom most frame. The {@code StackFrame} stream
 * is closed when the walk method returns. Throws {@link IllegalArgumentException} if an attempt
 * is made to reuse the closed stream.
 * <p>
 * The stack walking options of a {@code StackWalker} determines the information of {@code StackFrame}
 * objects to be returned. By default, stack frames of the reflection API and implementation
 * classes are hidden and {@code StackFrame}s have the class name and method name available but
 * not the class reference.
 * <p>
 * The {@code StackWalker} is thread-safe. Multiple threads can share a single {@code StackWalker}
 * object to traverse its own stack. A permission check is performed when a {@code StackWalker} is
 * created, according to the options it requests. No further permission check is done at stack
 * walking time.
 * <p>
 * This is a port version of Java 9 Feature "StackWalker".
 *
 * @apiNote Examples:
 *
 * @author Magic_Sweepy
 */
@ThreadSafe
public final class StackWalker
{

    public enum Option { SHOW_HIDDEN_FRAMES, RETAIN_CLASS_REFERENCE }

    private static final Map<Set<Option>, StackWalker> INSTANCES = Collections.synchronizedMap(new WeakHashMap<>());

    private final EnumSet<Option> options;
    /* package */ final boolean retainClassReference; // => Options.RETAIN_CLASS_REFERENCE

    private StackWalker(Set<Option> options)
    {
        this.options = options.contains(Option.SHOW_HIDDEN_FRAMES)
                ? EnumSet.of(Option.SHOW_HIDDEN_FRAMES) : EnumSet.noneOf(Option.class);
        this.retainClassReference = options.contains(Option.RETAIN_CLASS_REFERENCE);
    }

    /**
     * Returns a {@code StackWalker} instance with the given options specifying the {@code StackFrame}
     * information it can access.
     * <p>
     * If the given options is empty, this {@code StackWalker} is configured to skip all hidden
     * frames and no class reference is retained.
     * <p>
     * If a security manager is present and the given options contains {@code RETAIN_CLASS_REFERENCE},
     * it calls its {@code checkPermission} method for {@code RuntimePermission("getStackWalkerWithClassReference")}.
     *
     * @throws SecurityException If a security manager exists and its {@code checkPermission}
     *                           method denies access.
     *
     * @param options Stack walking option in {@link Option}.
     * @return        A {@code StackWalker} configured with the given options.
     */
    public static StackWalker getInstance(Set<Option> options)
    {
        return INSTANCES.computeIfAbsent(Collections.unmodifiableSet(new HashSet<>(options)),
                StackWalker::new);
    }

    /**
     * Applies the given function to the stream of {@code StackFrame}s for the current thread,
     * traversing from the top frame of the stack, which is the method calling this walk method.
     * <p>
     * The {@code StackFrame} stream will be closed when this method returns.
     *
     * @param walker A function that takes a stream of stack frames and returns a result.
     * @return       The result of applying the function to the stream of {@code StackFrame}.
     *
     * @param <T>    The type of the result of applying the function to the stream of {@code StackFrame}.
     *
     * @throws IllegalStateException When a closed {@code Stream<StackFrame>} object is reused.
     *
     * @apiNote For example, to find the first 10 calling frames, first skipping those frames
     *          whose declaring class is in package {@code com.foo}:
     *          <pre>{@code
     *              List<StackFrame> frames = StackWalker.getInstance()
     *                  .walk(s -> s.dropWhile(f -> f.getClassName().startsWith("com.foo."))
     *                  .limit(10)
     *                  .collect(Collectors.toList()));
     *          }</pre>
     *          This method takes a {@code Function} accepting a {@code Stream<StackFrame>},
     *          rather than returning a {@code Stream<StackFrame>} and allowing the caller to
     *          directly manipulate the stream. The ava JVM is free to reorganize a thread's
     *          control stack, for example, via deoptimization. By taking a {@code Function}
     *          parameter, this method allows access to stack frames through a stable view of
     *          a thread's control stack. Parallel execution is effectively disabled and stream
     *          pipeline execution will only occur on the current thread.
     *
     * @implNote The implementation stabilizes the stack by anchoring a frame specific to the
     *           stack walking and ensures that the stack walking is performed above the
     *           anchored frame. Throws {@code IllegalStateException} when the stream object i
     *           closed or being reused.
     */
    public <T> T walk(Function<? super Stream<StackFrame>, ? extends T> walker)
    {
        final StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        final List<StackFrame> frames = new ArrayList<>(elements.length);

        boolean skip = true;
        for (StackTraceElement element : elements)
        {
            if (skip)
            {
                skip = element.getClassName().startsWith(getClass().getName());
                continue;
            }
            frames.add(new StackFrameImpl(element, this));
        }
        return walker.apply(frames.stream());
    }

}
