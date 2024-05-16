package com.nd.tepia.entities.enums;

public enum UserStatus {
    FREE(10),
    BASIC(483898),
    PRO(397554),
    VIP(505026),
    BETA_TESTER(529011),
    DEV_FRONT_VIEWER(785923),
    DEV_BACK_VIEWER(734291),
    DEV_FULL_VIEWER(719293),
    DEV_FRONT_EDITOR(867239),
    DEV_BACK_EDITOR(834394),
    DEV_FULL_EDITOR(875923),
    ADM_VIEWER(1335384),
    ADM_HALF_ACCESSOR(1465623),
    ADM_FULL(1985882);

    private int code;
    private UserStatus(int code){this.code = code;}
    public int getCode(){return this.code;}
    public static UserStatus valueOf(int code){
        for (UserStatus value : UserStatus.values()) {
            if (code == value.getCode()) return value;
        }
        throw new IllegalArgumentException("Invalid UserStatus code!");
    }
}
