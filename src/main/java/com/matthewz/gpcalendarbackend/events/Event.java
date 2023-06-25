package com.matthewz.gpcalendarbackend.events;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    Date eventdate;

    @Setter
    @Getter
    String startTimeStr;
    Time startTimeForSql;

    Time endTimeForSql;

    String title;  String start;

    String end;




    public Time getEndTimeForSql() {
        return Time.valueOf(endTimeStr);
    }

    public Time getStartTimeForSql() {
        return Time.valueOf(startTimeStr);
    }
    public String getTitle() {
        return eventcmt;
    }

    public String getStart() {
        return dateFormat.format(this.eventdate) + "T"+ this.startTimeStr;
    }



    public String getEnd() {
        return dateFormat.format(this.eventdate) + "T"+ this.endTimeStr;
    }



    @Setter
    @Getter
    String endTimeStr;

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
