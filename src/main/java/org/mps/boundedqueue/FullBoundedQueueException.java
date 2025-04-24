package org.mps.boundedqueue;

public class FullBoundedQueueException extends RuntimeException {

    public FullBoundedQueueException(String msg) {
        super(msg);
    }
}
