package com.nd.tepia.services.exceptions;

public class BynameTryDuplicationException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public BynameTryDuplicationException(){super();}

    public BynameTryDuplicationException(String byname){
        super(String.format("The Byname [%s] is already registered in database", byname));
    }
    
}
