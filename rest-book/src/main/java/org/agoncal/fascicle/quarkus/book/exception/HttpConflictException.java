package org.agoncal.fascicle.quarkus.book.exception;

public class HttpConflictException extends RuntimeException{

    public HttpConflictException(String mensaje) {
        super(mensaje);
    }
}
