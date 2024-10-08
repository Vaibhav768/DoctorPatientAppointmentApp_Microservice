package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.PatientDetails;
import com.nt.exception.ResourceNotFoundException;
import com.nt.service.PatientAPIService;

@RestController
@RequestMapping("/patient-api")
public class PatientAPIController {

	@Autowired
	private PatientAPIService patientService;
	
	@PostMapping("/create-patient")
	public ResponseEntity<String> createPatient(@RequestBody PatientDetails patient)throws IllegalArgumentException{
		String resultMsg = patientService.createPatient(patient);
		return new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-patient")
	public ResponseEntity<String> updatePatient(@RequestBody PatientDetails patient)throws ResourceNotFoundException{
		String resultMsg = patientService.updatePatient(patient);
		return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/patient/{pid}")
	public ResponseEntity<PatientDetails> getPatientById(@PathVariable("pid") Long patientId)throws IllegalArgumentException{
		PatientDetails pdetails = patientService.getPatientById(patientId);
		return new ResponseEntity<>(pdetails,HttpStatus.OK);
	}
	
	@GetMapping("/all-patients")
	public ResponseEntity<List<PatientDetails>> getAllPatients() throws IllegalArgumentException{
		List<PatientDetails> patientList = patientService.getAllPatients();
		return new ResponseEntity<>(patientList,HttpStatus.OK);
	}
}
