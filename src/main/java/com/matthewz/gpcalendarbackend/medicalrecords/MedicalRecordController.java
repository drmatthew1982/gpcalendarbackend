package com.matthewz.gpcalendarbackend.medicalrecords;

import com.matthewz.gpcalendarbackend.common.Massage;
import com.matthewz.gpcalendarbackend.common.MeaasgeTextEnum;
import com.matthewz.gpcalendarbackend.events.Event;
import com.matthewz.gpcalendarbackend.mapper.EventMapper;
import com.matthewz.gpcalendarbackend.mapper.MedicalRecordMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MedicalRecordController {
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;
    @RequestMapping("/findmedicalrecordbyeventid")
    public ResponseEntity<Object> findMedicalRecordByEventId(String event_id, HttpServletResponse response) {
        List<MedicalRecord> medicalRecords = medicalRecordMapper.findMedicalRecordByEventId(event_id);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(medicalRecords,HttpStatus.OK);
    }
    @RequestMapping("/createmedicalrecord")
    public ResponseEntity<Object> createoclient(@RequestBody MedicalRecord medicalRecord, HttpServletResponse response) {
        medicalRecordMapper.createMedicalRecord(medicalRecord);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.CREATE_SUCCESS.getCode(),MeaasgeTextEnum.CREATE_SUCCESS.getText()),HttpStatus.OK);
    }
    @RequestMapping("/updatemedicalrecord")
    public ResponseEntity<Object> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, HttpServletResponse response) {
        medicalRecordMapper.updateMedicalRecord(medicalRecord);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.UPDATE_SUCCESS.getCode(),MeaasgeTextEnum.UPDATE_SUCCESS.getText()),HttpStatus.OK);
    }

}
