package com.matthewz.gpcalendarbackend.common;

public enum MeaasgeTextEnum {
    CREATE_SUCCESS("001","Created Success"),
    UPDATE_SUCCESS("001","Updated Success"),
    FAILED("002","Created Failed"),
    DUPLICATE("003","Duplicated Organisation Code");

    private String code;
    private String text;
    MeaasgeTextEnum(String code,String text) {
        this.code = code;
        this.text = text;
    }
    public String getCode() {
        return this.code;
    }
    public String getText() {
        return this.text;
    }

    public static MeaasgeTextEnum fromString(String text) {
        for (MeaasgeTextEnum b : MeaasgeTextEnum.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
