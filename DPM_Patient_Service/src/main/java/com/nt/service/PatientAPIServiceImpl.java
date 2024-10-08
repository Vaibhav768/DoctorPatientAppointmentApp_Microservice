package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.PatientDetails;
import com.nt.entity.PatientEntity;
import com.nt.repository.PatientAPIRepository;

@Service
public class PatientAPIServiceImpl implements PatientAPIService {

	@Autowired
	private PatientAPIRepository patientRepo;
	
	@Override
	public String createPatient(PatientDetails patient) {
		PatientEntity entity = new PatientEntity();
		BeanUtils.copyProperties(patient, entity);
		PatientEntity patientData = patientRepo.save(entity);
		return "Patient Name::"+patientData.getPatientName()+ " is saved with PatientId:: "+patientData.getPatientId();
	}
	
	@Override
	public String updatePatient(PatientDetails patient) {
		Long pid = patient.getPatientId();
		Optional<PatientEntity> patientData = patientRepo.findById(pid);
		if(patientData.isPresent()) {
				PatientEntity entity = new PatientEntity();
				BeanUtils.copyProperties(patient, entity);
				patientRepo.save(entity);
				return "Patient deatails for patientId:: "+patient.getPatientId()+" are updated sccessfully.";
			}
		return "Patient is not found for updation";
	}
												
	@Override
	public PatientDetails getPatientById(Long patientId) {
		PatientDetails patientDetails = new PatientDetails();
		//1st method 
		/*
		Optional<PatientEntity> patientData=patientRepo.findById(patientId); 
		if(patientData.isPresent()) {
			PatientEntity entity = patientData.get();
			BeanUtils.copyProperties(entity, patientDetails);
			return patientDetails;
		}*/
		//2nd method
		PatientEntity entity = patientRepo.findById(patientId).orElseThrow(()->new IllegalArgumentException("Patient not found.."));
		BeanUtils.copyProperties(entity, patientDetails);
		return patientDetails;
	}

	@Override
	public List<PatientDetails> getAllPatients() {
		List<PatientDetails> patientsDetails = new ArrayList<>();
		List<PatientEntity> patients = patientRepo.findAll();
		BeanUtils.copyProperties(patients, patientsDetails);
		return patientsDetails;
	}

}
