package com.nd.tepia.entities.enums;

import java.util.ArrayList;
import java.util.List;

public enum Language {
    SYSTEM_DEFAULT(0d),
    AF(1d),
    SQ(2d),
    AR_SA(3.01),
    AR_IQ(3.02),
    AR_EG(3.03),
    AR_LY(3.04),
    AR_DZ(3.05),
    AR_MA(3.06),
    AR_TN(3.07),
    AR_OM(3.08),
    AR_YE(3.09),
    AR_SY(3.10),
    AR_JO(3.11),
    AR_LB(3.12),
    AR_KW(3.13),
    AR_AE(3.14),
    AR_BH(3.15),
    AR_QA(3.16),
    EU(4d),
    BG(5d),
    BE(6d),
    CA(7d),
    ZH_TW(8.01),
    ZH_CN(8.02),
    ZH_HK(8.03),
    ZH_SH(8.04),
    HR(9d),
    CS(10d),
    DA(11d),
    NL(12.01),
    NL_BE(12.02),
    EN(13.01),
    EN_US(13.02),
    EN_GB(13.03),
    EN_AU(13.04),
    EN_CA(13.05),
    EN_NZ(13.06),
    EN_IE(13.07),
    EN_ZA(13.08),
    EN_JM(13.09),
    EN_BZ(13.10),
    EN_TT(13.11),
    ET(14d),
    FO(15d),
    FA(16d),
    FI(17d),
    FR(18.01),
    FR_BE(18.02),
    FR_CA(18.03),
    FR_CH(18.04),
    FR_LU(18.05),
    GD(19d),
    GA(20d),
    DE(21.01),
    DE_CH(21.02),
    DE_AT(21.03),
    DE_LU(21.04),
    DE_LI(21.05),
    EL(22d),
    HE(23d),
    HI(24d),
    HU(25d),
    IS(26d),
    ID(27d),
    IT(28.01),
    IT_CH(28.02),
    JA(29d),
    KO(30d),
    LV(31d),
    LT(32d),
    MK(33d),
    MS(34d),
    MT(35d),
    NO(36d),
    PL(37d),
    PT(38.01),
    PT_BR(38.02),
    RM(39d),
    RO(40.01),
    RO_MO(40.02),
    RU(41.01),
    RU_MO(41.02),
    SZ(42d),
    SR(43d),
    SK(44d),
    SL(45d),
    SB(46d),
    ES(47.01),
    ES_MX(47.02),
    ES_GT(47.03),
    ES_CR(47.04),
    ES_PA(47.05),
    ES_DO(47.06),
    ES_VE(47.07),
    ES_CO(47.08),
    ES_PE(47.09),
    ES_AR(47.10),
    ES_EC(47.11),
    ES_CL(47.12),
    ES_UY(47.13),
    ES_PY(47.14),
    ES_BO(47.15),
    ES_SV(47.16),
    ES_HN(47.17),
    ES_NI(47.18),
    ES_PR(47.19),
    SX(48d),
    SV(49.01),
    SV_FI(49.02),
    TH(50d),
    TS(51d),
    TN(52d),
    TR(53d),
    UK(54d),
    UR(55d),
    VE(56d),
    VI(57d),
    XH(58d),
    JI(59d),
    ZU(60d);

    private Double code;

    private Language(Double code){this.code = code;}
    public Double getCode(){return this.code;}

    public List<Language> getAll(){
        List<Language> languages = new ArrayList<>();
        for (Language al : values()) languages.add(al);
        return languages;
    }

    public static Language valueOf(Double code){
    for (Language value : Language.values()) {
    if (code == value.getCode()) return value;
    }
    throw new IllegalArgumentException("Invalid Language code!");
    }
}