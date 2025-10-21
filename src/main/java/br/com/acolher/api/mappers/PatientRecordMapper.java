package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.PatientRecordCreateDTO;
import br.com.acolher.api.dtos.PatientRecordResponseDTO;
import br.com.acolher.api.entities.Appointment;
import br.com.acolher.api.entities.PatientRecord;

public class PatientRecordMapper {

    public static PatientRecord toEntity(PatientRecordCreateDTO patientRecordDTO, Appointment appointment, byte[] file, String fileType) {
        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setAppointment(appointment);
        patientRecord.setAppointmentSummary(patientRecordDTO.appointmentSummary());
        patientRecord.setRecordDate(patientRecordDTO.recordDate());
        patientRecord.setFile(file);
        patientRecord.setFileType(fileType);
        return patientRecord;
    }

    public static PatientRecordResponseDTO toDTO(PatientRecord patientRecord) {
        return new PatientRecordResponseDTO(patientRecord.getId(), patientRecord.getAppointment().getId(), patientRecord.getAppointmentSummary(), patientRecord.getRecordDate());
    }
}
