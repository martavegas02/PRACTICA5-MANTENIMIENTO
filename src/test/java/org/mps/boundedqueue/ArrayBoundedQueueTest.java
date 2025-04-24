package org.mps.boundedqueue;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArrayBoundedQueueTest {
    

    /*
     * Constructor
     * 1. Comprobar que el constructor lanza una excepción si la capacidad es negativa o cero.
     * 2. Comprobar que el constructor crea un objeto ArrayBoundedQueue con la capacidad correcta.
     * 3. Comprobar que el constructor crea un objeto ArrayBoundedQueue vacío.
     */

    @Test
    public void testConstructor() {
        assertThatThrownBy( () -> new ArrayBoundedQueue<>(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ArrayBoundedException: capacity must be positive");

        ArrayBoundedQueue <Integer> queue = new ArrayBoundedQueue<>(5);
        
        assertThat(queue)
            .isNotNull();

        assertThat(queue.size())
            .isZero();
        
        assertThat(queue.isEmpty())
            .isTrue();

        assertThat(queue.getFirst())
            .isEqualTo(0);

        assertThat(queue.getLast())
            .isEqualTo(0);

    }


    /*   put
     * 1. Comprobar que el método put lanza una excepción si la cola está llena.
     * 2. Comprobar que el método put lanza una excepción si el elemento es nulo.
     * 3. Comprobar que el método put añade un elemento a la cola y actualiza el tamaño correctamente.
     */ 

    @Test
    public void testPut() {
        ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);
        
        queue.put(1);
        queue.put(2);
        queue.put(3);


        assertThatThrownBy(() -> queue.put(null))
            .isInstanceOf(FullBoundedQueueException.class)
            .hasMessageContaining("put: full bounded queue");

        assertThat(queue.isFull())
            .isTrue();

        assertThat(queue.get())
            .isEqualTo(1);

        assertThatThrownBy(() -> queue.put(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("put: element cannot be null");

        assertThat(queue.size())
            .isEqualTo(2);
    }

    /*   get
        * 1. Comprobar que el método get lanza una excepción si la cola está vacía.
        * 2. Comprobar que el método get devuelve el primer elemento de la cola y actualiza el tamaño correctamente.
    */

    /*   advance 
     * 1. Comprobar que el método advance devuelve el índice correcto del siguiente elemento en la cola.
     */

    /*   isFull
     * 1. Comprobar que el método isFull devuelve true si la cola está llena.
     * 2. Comprobar que el método isFull devuelve false si la cola no está llena.
     */

    /*   isEmpty
        * 1. Comprobar que el método isEmpty devuelve true si la cola está vacía.
        * 2. Comprobar que el método isEmpty devuelve false si la cola no está vacía.
    */

    /*  getFirst
        * 1. Comprobar que el método getFirst devuelve el índice del primer elemento de la cola.
     */

    /*  getLast
     * 1. Comprobar que el método getLast devuelve el índice del último elemento de la cola.
     */

    
}
