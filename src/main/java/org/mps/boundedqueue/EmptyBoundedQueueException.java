package org.mps.boundedqueue;

public class EmptyBoundedQueueException extends RuntimeException {

    public EmptyBoundedQueueException(String msg) {
        super(msg);
    }
}
