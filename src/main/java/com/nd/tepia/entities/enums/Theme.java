package com.nd.tepia.entities.enums;

public enum Theme {
    LIGHT_BLUE(0),
    LIGHT_GREEN(1),
    LIGHT_RED(2),
    LIGHT_PURPLE(3),
    DARK_BLUE(4),
    DARK_GREEN(5),
    DARK_RED(6),
    DARK_PURPLE(7);

    private int code;
    private Theme(int code){this.code = code;}
    public int getCode(){return this.code;}
    public static Theme valueOf(int code){
        for (Theme value : Theme.values()) {
            if (code == value.getCode()) return value;
        }
        throw new IllegalArgumentException("Invalid Theme code!");
    }
}
