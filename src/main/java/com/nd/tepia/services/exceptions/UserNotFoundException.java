package com.nd.tepia.services.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String userParam, String paramValue){
        super(String.format("User not found by %s using value: %s", userParam, paramValue));
    }
}