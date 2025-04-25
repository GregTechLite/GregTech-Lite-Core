package magicbook.gtlitecore.api.utils.maps;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection that has a well-defined encounter order, that supports operations at both
 * ends, and that is reversible. The elements of a sequenced collection have an encounter
 * order, where conceptually the elements have a linear arrangement from the first element
 * to the last element. Given any two elements, one element is either before (closer to the
 * first element) or after (closer to the last element) the other element.
 * <p>
 * Note that this definition does not imply anything about physical positioning of elements,
 * such as their locations in a computer's memory.
 * <p>
 * Several methods inherited from the {@link Collection} interface are required to operate
 * on elements according to this collection's encounter order. For instance, the iterator
 * method provides elements starting from the first element, proceeding through successive
 * elements, until the last element. Other methods that are required to operate on elements
 * in encounter order include the following: {@code forEach}, {@code parallelStream},
 * {@code spliterator}, {@code stream}, and all overloads of the {@code toArray} method.
 * <p>
 * This interface provides methods to add, retrieve, and remove elements at either end of
 * the collection.
 * <p>
 * This interface also defines the reversed method, which provides a reverse-ordered view
 * of this collection. In the reverse-ordered view, the concepts of first and last are
 * inverted, as are the concepts of successor and predecessor. The first element of this
 * collection is the last element of the reverse-ordered view, and vice-versa. The successor
 * of some element in this collection is its predecessor in the reversed view, and
 * vice-versa. All methods that respect the encounter order of the collection operate as
 * if the encounter order is inverted. For instance, the {@link Collection#iterator()} method
 * of the reversed view reports the elements in order from the last element of this collection
 * to the first. The availability of the reversed method, and its impact on the ordering
 * semantics of all applicable methods, allow convenient iteration, searching, copying, and
 * streaming of the elements of this collection in either forward order or reverse order.
 * <p>
 * This class is the port of Java 21 Feature "Sequenced Collection".
 *
 * @apiNote This interface does not impose any requirements on the {@code equals} and
 *          {@code hashCode} methods, because requirements imposed by sub-interfaces list
 *          and sequenced set (which inherits requirements from set) would be in conflict.
 *          See the specifications for {@link Collection#equals} and {@link Collection#hashCode}
 *          for further information.
 *
 * @param <E>
 */
public interface SequencedCollection<E> extends Collection<E>
{

    /**
     * Returns a reverse-ordered view of this collection. The encounter order of elements
     * in the returned view is the inverse of the encounter order of elements in this
     * collection. The reverse ordering affects all order-sensitive operations, including
     * those on the view collections of the returned view. If the collection implementation
     * permits modifications to this view, the modifications "write through" to the underlying
     * collection. Changes to the underlying collection might or might not be visible in
     * this reversed view, depending upon the implementation.
     *
     * @return A reverse-ordered view of this collection.
     */
    SequencedCollection<E> reversed();

    /**
     * Adds an element as the first element of this collection (optional operation).
     * After this operation completes normally, the given element will be a member of this
     * collection, and it will be the first element in encounter order.
     *
     * @param e The element to be added.
     *
     * @throws NullPointerException If the specified element is null and this collection
     *                              does not permit null elements.
     * @throws UnsupportedOperationException If this collection implementation does not
     *                                       support this operation.
     *
     * @implSpec The implementation in this interface always throws {@link UnsupportedOperationException}.
     */
    default void addFirst(E e)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds an element as the last element of this collection (optional operation). After
     * this operation completes normally, the given element will be a member of this collection,
     * and it will be the last element in encounter order.
     *
     * @param e The element to be added.
     *
     * @throws NullPointerException If the specified element is null and this collection does
     *                              not permit null elements.
     * @throws UnsupportedOperationException If this collection implementation does not support
     *                                       this operation.
     *
     * @implSpec The implementation in this interface always throws {@link UnsupportedOperationException}.
     */
    default void addLast(E e)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the first element of this collection.
     *
     * @return The retrieved element.
     *
     * @throws NoSuchElementException If this collection is empty.
     *
     * @implSpec The implementation in this interface obtains an iterator of this collection,
     *           and then it obtains an element by calling the iterator's next method. Any
     *           {@link NoSuchElementException} thrown is propagated. Otherwise, it returns
     *           the element.
     */
    default E getFirst()
    {
        return this.iterator().next();
    }

    /**
     * Gets the last element of this collection.
     *
     * @return The retrieved element.
     *
     * @throws NoSuchElementException If this collection is empty.
     *
     * @implSpec The implementation in this interface obtains an iterator of the reversed
     *           view of this collection, and then it obtains an element by calling the
     *           iterator's next method. Any {@link NoSuchElementException} thrown is
     *           propagated. Otherwise, it returns the element.
     */
    default E getLast()
    {
        return this.reversed().iterator().next();
    }

    /**
     * Removes and returns the first element of this collection (optional operation).
     *
     * @return The removed element.
     *
     * @throws NoSuchElementException If this collection is empty.
     * @throws UnsupportedOperationException If this collection implementation does not
     *                                       support this operation.
     *
     * @implSpec The implementation in this interface obtains an iterator of this collection,
     *           and then it obtains an element by calling the iterator's next method. Any
     *           {@link NoSuchElementException} thrown is propagated. It then calls the
     *           iterator's remove method. Any {@link UnsupportedOperationException} thrown
     *           is propagated. Then, it returns the element.
     */
    default E removeFirst()
    {
        Iterator<E> it = this.iterator();
        E e = it.next();
        it.remove();
        return e;
    }

    /**
     * Removes and returns the last element of this collection (optional operation).
     *
     * @return The removed element.
     *
     * @throws NoSuchElementException If this collection is empty.
     * @throws UnsupportedOperationException If this collection implementation does not support this operation.
     *
     * @implSpec The implementation in this interface obtains an iterator of the reversed
     *           view of this collection, and then it obtains an element by calling the
     *           iterator's next method. Any {@link NoSuchElementException} thrown is
     *           propagated. It then calls the iterator's remove method. Any
     *           {@link UnsupportedOperationException} thrown is propagated. Then, it
     *           returns the element.
     */
    default E removeLast()
    {
        Iterator<E> it = this.reversed().iterator();
        E e = it.next();
        it.remove();
        return e;
    }

}
