package com.matthewz.gpcalendarbackend.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Event {
    @Setter
    @Getter
    String id;

    @Setter
    @Getter
    String eventcmt;

    @Setter
    @Getter
    String client_id;

    @Setter
    @Getter
    String org_id;

    @Setter
    @Getter
    String createby;

    @Setter
    @Getter
    String assigned_to;

    @Setter
    @Getter
    Date startTime;

    @Setter
    @Getter
    Date endTime;

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
    String reportStatus;

}
