package org.mps.boundedqueue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

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
    @Test 
    public void testGet(){
        ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);

        queue.put(7);
        queue.put(8);
        queue.put(9);

        assertThat(queue.get())
            .isEqualTo(7);

        assertThat(queue.size())
            .isEqualTo(2);

        assertThat(queue.getFirst())
            .isEqualTo(1);

        assertThat(queue.getLast())
            .isEqualTo(3);

        queue.get();
        queue.get();

        assertThatThrownBy(() -> queue.get())
            .isInstanceOf(EmptyBoundedQueueException.class)
            .hasMessageContaining("get: empty bounded queue");
            
    }

    

    
}
