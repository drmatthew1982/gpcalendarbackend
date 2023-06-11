package com.matthewz.gpcalendarbackend.clients;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

public class Client {
    @Setter
    @Getter
    String id;

    @Setter
    @Getter
    String firstname;

    @Setter
    @Getter
    String middlename;

    @Setter
    @Getter
    String lastname;

    @Setter
    @Getter
    String created_user_id;

    @Setter
    @Getter
    Date created_time;

    @Setter
    @Getter
    String modified_user_id;

    @Setter
    @Getter
    Date modified_time;

    @Setter
    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthday;

    @Setter
    @Getter
    String gender;

    @Setter
    @Getter
    String client_id_no;

    @Setter
    @Getter
    String user_id_for_query;
}
