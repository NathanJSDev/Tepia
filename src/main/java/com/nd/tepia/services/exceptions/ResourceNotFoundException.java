package com.nd.tepia.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1l;
    
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String resourceName){
        super(String.format("Resource [%s] not found.", resourceName));
    }
}
