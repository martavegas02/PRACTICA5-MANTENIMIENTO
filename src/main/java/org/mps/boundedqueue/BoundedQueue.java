package org.mps.boundedqueue;

/**
 * A bounded queue is a queue that can hold a bounded number of elements.
 *
 * @param <T> the type of elements held in this bounded queue
 */
public interface BoundedQueue<T> extends Iterable<T> {

    /**
     * Inserts the specified value at the end of this bounded queue.
     *
     * @param value the value to be inserted
     * @throws FullBoundedQueueException is this bounded queue is full
     * @throws IllegalArgumentException  if the value to be inserted is {@code null}
     */
    void put(T value);

    /**
     * Returns the first element of this bounded queue.
     *
     * @return the first element of this bounded queue
     * @throws EmptyBoundedQueueException if this bounded queue is empty
     */
    T get();

    /**
     * Returns {@code true} if this bounded queue is full.
     *
     * @return {@code true} if this bounded queue is full, {@code false} otherwise
     */
    boolean isFull();

    /**
     * Returns {@code true} if this bounded queue is empty.
     *
     * @return {@code true} if this bounded queue is empty, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this bounded queue.
     *
     * @return the number of elements in this bounded queque
     */
    int size();


    /**
     * Returns the index of the first element in the que.
     *
     * @return the index of the first element in the que.
     */
    int getFirst();

    /**
     * Returns the index of the last element in the que.
     *
     * @return the index of the last element in the que.
     */
    int getLast();
}
