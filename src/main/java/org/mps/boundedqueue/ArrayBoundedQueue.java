package org.mps.boundedqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBoundedQueue<T> implements BoundedQueue<T> {

    /**
     * <h3>Internal representation:</h3>
     * <p>
     * The elements of the bounded queue are stored in the array {@code buffer} as follows:
     * </p>
     * <ul>
     * <li> elements are stored in a circular slice of {@code size} positions that ranges from {@code first} to
     * {@code nextFree-1}, where the next position is computed using modular arithmetic. Elements cannot
     * be {@code null}.
     * </li>
     * <li> the {@code size - buffer.length} available positions are those in a circular slice that ranges from
     * {@code nextFree} to {@code first-1}, once again, using modular arithmetic. Available positions
     * must be {@code null}.
     * </li>
     * </ul>
     */

    private final T[] buffer; // stores the elements of the bounded queue
    private int first; // index of the position in buffer that stores the first element in the bounded queue
    private int nextFree; // index of the first position available in the array buffer
    private int size; // number of elements in the bounded queue

    /**
     * Constructs an empty bounded queue with the specified capacity.
     *
     * @param capacity the capacity of the bounded queue
     * @throws IllegalArgumentException if the specified capacity is not positive
     */
    @SuppressWarnings("unchecked")
    public ArrayBoundedQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("ArrayBoundedException: capacity must be positive");
        }

        buffer = (T[]) new Object[capacity];
        first = 0;
        nextFree = 0;
        size = 0;
    }

    @Override
    public void put(T value) {
        if (isFull()) {
            throw new FullBoundedQueueException("put: full bounded queue");
        }
        if (value == null) {
            throw new IllegalArgumentException("put: element cannot be null");
        }

        buffer[nextFree] = value;
        nextFree = advance(nextFree);
        size++;
    }

    @Override
    public T get() {
        if (isEmpty()) {
            throw new EmptyBoundedQueueException("get: empty bounded queue");
        }

        T item = buffer[first];
        buffer[first] = null;
        first = advance(first);
        size--;
        return item;
    }

    private int advance(int index) {
        return (index + 1) % buffer.length;
    }

    @Override
    public boolean isFull() {
        return size == buffer.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getFirst() {
        return this.first;
    }

    @Override
    public int getLast() {
        return this.nextFree;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayBoundedQueueIterator();
    }

    private class ArrayBoundedQueueIterator implements Iterator<T> {

        private int current; // index of the element to be visited
        private int visited; // number of visited elements

        public ArrayBoundedQueueIterator() {
            current = first;
            visited = 0;
        }

        @Override
        public boolean hasNext() {
            return visited < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next: bounded queue iterator exhausted");
            }
            T item = buffer[current];
            current = advance(current);
            visited++;
            return item;
        }
    }
}
