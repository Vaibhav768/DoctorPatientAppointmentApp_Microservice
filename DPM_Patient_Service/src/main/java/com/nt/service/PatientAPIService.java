package com.nt.service;

import java.util.List;

import com.nt.binding.PatientDetails;

public interface PatientAPIService {
	public String createPatient(PatientDetails input);
	public String updatePatient( PatientDetails patient);
	public PatientDetails getPatientById(Long patientId);
	public List<PatientDetails> getAllPatients();
}
