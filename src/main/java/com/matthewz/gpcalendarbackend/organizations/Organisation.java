package com.matthewz.gpcalendarbackend.organizations;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class Organisation {

    @Setter
    @Getter
    String id;

    @Setter
    @Getter
    String org_name;

    @Setter
    @Getter
    String org_code;

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



}
