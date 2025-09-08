package org.agoncal.fascicle.quarkus.book.exception;

public class HttpBadResquestException extends RuntimeException{

    public HttpBadResquestException(String mensaje) {
        super(mensaje);
    }
}
