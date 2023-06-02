package com.matthewz.gpcalendarbackend.common;

import lombok.Getter;
import lombok.Setter;

public class Massage {

    public Massage (String code,String message){
        this.code = code;
        this.message = message;
    }

    @Setter
    @Getter
    String code;

    @Setter
    @Getter
    String message;


}
