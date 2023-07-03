package org.example;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String m) {
        super(m);
    }
}
