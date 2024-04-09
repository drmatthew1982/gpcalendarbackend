package com.matthewz.gpcalendarbackend.events;

import com.matthewz.gpcalendarbackend.clients.Client;
import com.matthewz.gpcalendarbackend.common.Massage;
import com.matthewz.gpcalendarbackend.common.MeaasgeTextEnum;
import com.matthewz.gpcalendarbackend.mapper.ClientMapper;
import com.matthewz.gpcalendarbackend.mapper.EventMapper;
import com.matthewz.gpcalendarbackend.mapper.MedicalRecordMapper;
import com.matthewz.gpcalendarbackend.medicalrecords.MedicalRecord;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;
    @RequestMapping("/findeventsbyuserid")
    public ResponseEntity<Object> findEventssByUserId(String user_id,Date current_date, HttpServletResponse response) {
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
    public ResponseEntity<Object> createEvent(@RequestBody Event event, HttpServletResponse response) {
        eventMapper.createEvent(event);
        List<MedicalRecord> recordlist = medicalRecordMapper.findMedicalRecordByEventId(event.getId());
        if(recordlist.size()==0){
            MedicalRecord medicalRecord =  new MedicalRecord();
            medicalRecord.setEventid(event.getId());
            medicalRecord.setCreated_user_id(event.getCreated_user_id());
            medicalRecordMapper.createMedicalRecord(medicalRecord);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.CREATE_SUCCESS.getCode(),MeaasgeTextEnum.CREATE_SUCCESS.getText()),HttpStatus.OK);

    }

    @RequestMapping("/updateevent")
    public ResponseEntity<Object> updateclient(@RequestBody Event event, HttpServletResponse response) {
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
