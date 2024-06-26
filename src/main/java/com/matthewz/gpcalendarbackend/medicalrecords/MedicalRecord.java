package com.matthewz.gpcalendarbackend.medicalrecords;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicalRecord {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Setter
    @Getter
    String id;

    @Setter
    @Getter
    String eventid;

    @Setter
    @Getter
    String summary;

    @Setter
    @Getter
    String positions;

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
