package org.agoncal.fascicle.quarkus.book.exception;

public class HttpNotFoundException extends RuntimeException{

    public HttpNotFoundException(String mensaje) {
        super(mensaje);
    }
}
