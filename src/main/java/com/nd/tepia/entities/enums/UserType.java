package com.nd.tepia.entities.enums;

public enum UserType {
    UNDEFINED(0),
    CIVIL(341242),
    JURIDIC(76482);

    private int code;
    private UserType(int code){this.code = code;}
    public int getCode(){return this.code;}
    public static UserType valueOf(int code){
        for (UserType value : UserType.values()) {
            if (code == value.getCode()) return value;
        }
        throw new IllegalArgumentException("Invalid UserType code!");
    }
}
