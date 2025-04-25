package magicbook.gtlitecore.api.utils.maps;

import java.util.Set;

/**
 * A collection that is both a {@link SequencedCollection} and a {@link Set}. As such, it
 * can be thought of either as a {@link Set} that also has a well-defined encounter order,
 * or as a {@link SequencedCollection} that also has unique elements.
 * <p>
 * This interface has the same requirements on the {@code equals} and {@code hashCode} methods
 * as defined by {@link Set#equals} and {@link Set#hashCode}. Thus, a {@link Set} and a
 * {@link SequencedSet} will compare equals if and only if they have equal elements,
 * irrespective of ordering.
 * <p>
 * A {@link SequencedSet} defines the {@link #reversed} method, which provides a reverse-ordered
 * view of this set. The only difference from the {@link SequencedCollection#reversed} method
 * is that the return type of {@link SequencedSet#reversed} is {@link SequencedSet}.
 * <p>
 * This class is the port of Java 21 Feature "Sequenced Collection", in Java 21, some sets
 * like {@link java.util.SortedSet} extends this class, but we cannot let them extends this
 * class yet, so we add some utils sequenced sets.
 *
 * @param <E>
 */
public interface SequencedSet<E> extends SequencedCollection<E>, Set<E>
{

    /**
     * Returns a reverse-ordered view of this collection. The encounter order of elements
     * in the returned view is the inverse of the encounter order of elements in this
     * collection. The reverse ordering affects all order-sensitive operations, including
     * those on the view collections of the returned view. If the collection implementation
     * permits modifications to this view, the modifications "write through" to the underlying
     * collection. Changes to the underlying collection might or might not be visible in this
     * reversed view, depending upon the implementation.
     *
     * @return A reverse-ordered view of this collection, as a {@link SequencedSet}.
     *
     * @see SequencedCollection#reversed()
     */
    SequencedSet<E> reversed();

}
