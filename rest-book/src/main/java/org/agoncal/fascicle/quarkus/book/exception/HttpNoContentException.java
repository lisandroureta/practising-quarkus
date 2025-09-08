package org.agoncal.fascicle.quarkus.book.exception;

public class HttpNoContentException extends RuntimeException{

    public HttpNoContentException(String mensaje){
        super(mensaje);
    }

    public HttpNoContentException(){
        super();
    }
}
