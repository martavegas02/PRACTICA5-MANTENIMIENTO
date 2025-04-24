package org.mps.boundedqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;

public class ArrayBoundedQueueTest {

    @Nested
    @DisplayName("Tests del Constructor")
    class ConstructorTests {

        private ArrayBoundedQueue<Integer> queue;

        @BeforeEach
        void setUp() {
            // Inicializamos la cola válida para los tests que lo requieran.
            queue = new ArrayBoundedQueue<>(5);
        }

        @Test
        public void testConstructor() {
            // 1. Comprobar que el constructor lanza una excepción si la capacidad es negativa o cero.
            assertThatThrownBy(() -> new ArrayBoundedQueue<>(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ArrayBoundedException: capacity must be positive");

            // 2. Comprobar que el constructor crea un objeto ArrayBoundedQueue con la capacidad correcta.
            assertThat(queue).isNotNull();

            // 3. Comprobar que el constructor crea un objeto ArrayBoundedQueue vacío.
            assertThat(queue.size()).isZero();
            assertThat(queue.isEmpty()).isTrue();
            assertThat(queue.getFirst()).isEqualTo(0);
            assertThat(queue.getLast()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Tests del método put")
    class PutTests {

        private ArrayBoundedQueue<Integer> queue;

        @BeforeEach
        void setUp() {
            // Inicializamos la cola con capacidad 3 para testear el método put.
            queue = new ArrayBoundedQueue<>(3);
        }

        @Test
        public void testPut() {
            queue.put(1);
            queue.put(2);
            queue.put(3);

            // 1. Comprobar que el método put lanza una excepción si la cola está llena.
            assertThatThrownBy(() -> queue.put(null))
                .isInstanceOf(FullBoundedQueueException.class)
                .hasMessageContaining("put: full bounded queue");

            assertThat(queue.isFull()).isTrue();
            assertThat(queue.get()).isEqualTo(1);

            // 2. Comprobar que el método put lanza una excepción si el elemento es nulo.
            assertThatThrownBy(() -> queue.put(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("put: element cannot be null");

            // 3. Comprobar que el método put añade un elemento y actualiza el tamaño.
            assertThat(queue.size()).isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("Tests del método get")
    class GetTests {

        private ArrayBoundedQueue<Integer> queue;

        @BeforeEach
        void setUp() {
            // Inicializamos la cola con capacidad 5 para testear el método get.
            queue = new ArrayBoundedQueue<>(5);
        }

        @Test
        public void testGet() {
            queue.put(7);
            queue.put(8);
            queue.put(9);

            // 1. Comprobar que el método get devuelve el primer elemento y actualiza el tamaño.
            assertThat(queue.get()).isEqualTo(7);
            assertThat(queue.size()).isEqualTo(2);
            assertThat(queue.getFirst()).isEqualTo(1);
            assertThat(queue.getLast()).isEqualTo(3);

            queue.get();
            queue.get();

            // 2. Comprobar que el método get lanza una excepción si la cola está vacía.
            assertThatThrownBy(() -> queue.get())
                .isInstanceOf(EmptyBoundedQueueException.class)
                .hasMessageContaining("get: empty bounded queue");
        }
    }

    @Nested
    @DisplayName("Tests del Iterador")
    class IteratorTests {

        private ArrayBoundedQueue<Integer> queue;
        private Iterator<Integer> iterator;

        @BeforeEach
        void setUp() {
            // Inicializamos la cola y su iterador para testear el comportamiento del iterador.
            queue = new ArrayBoundedQueue<>(3);
            iterator = queue.iterator();
        }

        @Test
        @DisplayName("El iterador debe devolver los elementos en el orden correcto")
        void testIterator() {
            queue.put(1);
            queue.put(2);
            queue.put(3);

            int[] expected = {1, 2, 3};
            int index = 0;

            while (iterator.hasNext()) {
                Integer value = iterator.next();
                assertThat(value).isNotNull().isEqualTo(expected[index]);
                index++;
            }
        }

        @Test
        @DisplayName("next debe lanzar una excepción si no hay más elementos")
        void testIteratorNoMoreElements() {
            queue.put(1);
            queue.put(2);
            queue.put(3);

            // Se crea un nuevo iterador para garantizar que se recorra desde el inicio.
            Iterator<Integer> iterator = queue.iterator();
            iterator.next();
            iterator.next();
            iterator.next();

            assertThatExceptionOfType(java.util.NoSuchElementException.class)
                .isThrownBy(() -> iterator.next())
                .withMessage("next: bounded queue iterator exhausted");
        }
    }
}
