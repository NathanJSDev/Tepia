package com.nd.tepia.entities.enums;

public enum OrderStatus {
    WAITTING_PAYMENT(1),
    PAID(2),
    CANCELED(3),
    REFUNDED(4);

    private int code;

    private OrderStatus(int code){this.code = code;}
    public int getCode(){return this.code;}

    public static OrderStatus valueOf(int code){
        for (OrderStatus value : OrderStatus.values()) {
            if (code == value.getCode()) return value;
        }
        throw new IllegalArgumentException("Invalid OrderStatus code!");
    }
}
