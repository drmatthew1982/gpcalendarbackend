package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.medicalrecords.MedicalRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "medicalRecordMapper")
public interface MedicalRecordMapper {
    List<MedicalRecord> findMedicalRecordByEventId(@Param("eventid")String eventid);
    void createMedicalRecord(@Param("medicalRecord")MedicalRecord medicalRecord);
    void updateMedicalRecord(@Param("medicalRecord")MedicalRecord medicalRecord);
}
