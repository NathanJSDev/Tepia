package com.nd.tepia.entities.enums;

public enum CardFlag {
    MASTERCARD(1),
    VISA(2),
    ELO(3),
    AMERICAN_EXPRESS(4),
    HIPERCARD(5),
    ALELO(6);

    private int code;

    private CardFlag(int code){this.code = code;}
    public int getCode(){return this.code;}

    public static CardFlag valueOf(int code){
        for (CardFlag value : CardFlag.values()) {
            if (code == value.getCode()) return value;
        }
        throw new IllegalArgumentException("Invalid CardFlag code!");
    }
}
