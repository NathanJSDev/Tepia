package com.nd.tepia.services.exceptions;

public class EmailTryDuplicationException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public EmailTryDuplicationException(){super();}

    public EmailTryDuplicationException(String email){
        super(String.format("The Email [%s] is already registered in database", email));
    }
    
}
