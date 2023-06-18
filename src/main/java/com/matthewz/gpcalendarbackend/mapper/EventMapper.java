package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.clients.Client;
import com.matthewz.gpcalendarbackend.events.Event;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "eventMapper")
public interface EventMapper {
    List<Event> findEventUserId(@Param("user_id") String user_id);

    void createEvent(@Param("event") Event Event);
    void updateEvent(@Param("event")Event Event);

}
