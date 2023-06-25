package com.matthewz.gpcalendarbackend.events;

import com.matthewz.gpcalendarbackend.clients.Client;
import com.matthewz.gpcalendarbackend.common.Massage;
import com.matthewz.gpcalendarbackend.common.MeaasgeTextEnum;
import com.matthewz.gpcalendarbackend.mapper.ClientMapper;
import com.matthewz.gpcalendarbackend.mapper.EventMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EventController {
    @Autowired
    private EventMapper eventMapper;
    @RequestMapping("/findeventsbyuserid")
    public ResponseEntity<Object> findClientsByUserId(String user_id,Date current_date, HttpServletResponse response) {
        Calendar lastMontheCalendar = Calendar.getInstance();
        lastMontheCalendar.setTime(current_date);
        lastMontheCalendar.add(Calendar.MONTH, -1);
        lastMontheCalendar.set(Calendar.DAY_OF_MONTH,15);
        Calendar nextMontheCalendar = Calendar.getInstance();
        nextMontheCalendar.setTime(current_date);
        nextMontheCalendar.add(Calendar.MONTH, 1);
        nextMontheCalendar.set(Calendar.DAY_OF_MONTH,15);
        List<Event> events = eventMapper.findEventsByUserId
                (user_id,new java.sql.Date(lastMontheCalendar.getTimeInMillis()),new java.sql.Date(nextMontheCalendar.getTimeInMillis()));
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(events,HttpStatus.OK);
    }
    @RequestMapping("/createevent")
    public ResponseEntity<Object> createoclient(Event event, HttpServletResponse response) {
        eventMapper.createEvent(event);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.CREATE_SUCCESS.getCode(),MeaasgeTextEnum.CREATE_SUCCESS.getText()),HttpStatus.OK);

    }

    @RequestMapping("/updateeventt")
    public ResponseEntity<Object> updateclient(Event event, HttpServletResponse response) {
        eventMapper.updateEvent(event);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.UPDATE_SUCCESS.getCode(),MeaasgeTextEnum.UPDATE_SUCCESS.getText()),HttpStatus.OK);
    }
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
    }
}
