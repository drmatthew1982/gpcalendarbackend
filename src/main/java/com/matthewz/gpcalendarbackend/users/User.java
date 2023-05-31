package com.matthewz.gpcalendarbackend.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class User implements Serializable {


    @Setter
    @Getter
    String id;

    @Setter
    @Getter
    String username;

    @Setter
    @Getter
    String password;

    @Setter
    @Getter
    String firstname;

    @Setter
    @Getter
    String middlename;

    @Setter
    @Getter
    String lastname;

}
