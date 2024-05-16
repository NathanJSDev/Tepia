package com.nd.tepia.services.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public IncorrectPasswordException(){super();}

    public IncorrectPasswordException(String password, String userName){
        super(String.format("Incorrect password [%s] for user [%s]", password, userName));
    }
    
}
