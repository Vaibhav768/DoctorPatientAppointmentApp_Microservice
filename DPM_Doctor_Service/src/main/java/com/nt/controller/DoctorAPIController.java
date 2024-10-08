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

import com.nt.binding.ClinicDTO;
import com.nt.binding.DoctorDTO;
import com.nt.exception.ResourceNotFoundException;
import com.nt.service.ClinicAPIService;
import com.nt.service.DoctorAPIService;

@RestController
@RequestMapping("/doctorClinic-api")
public class DoctorAPIController {

	@Autowired
	private DoctorAPIService doctorService;
	
	@Autowired
	private ClinicAPIService clinicService;
	
	//Doctor Service Controller Methods
	@PostMapping("/create-doctor")
	public ResponseEntity<String> createDoctor(@RequestBody DoctorDTO input) throws ResourceNotFoundException {
		String resultMsg = doctorService.createDoctor(input);
		return new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-doctor/{did}")
	public ResponseEntity<String> updateDoctor(@PathVariable("did")Long doctorId, @RequestBody DoctorDTO input) throws ResourceNotFoundException{
		String resultMsg = doctorService.updateDoctor(doctorId,input);
		return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/doctor/{did}")
	public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable("did") Long doctorId)throws ResourceNotFoundException{
		DoctorDTO ddetails = doctorService.getDoctorById(doctorId);
		return new ResponseEntity<>(ddetails,HttpStatus.OK);
	}
	
	@GetMapping("/all-doctors")
	public ResponseEntity<List<DoctorDTO>> getAllDoctorsList()throws Exception{
		List<DoctorDTO> doctorList = doctorService.getAllDoctorsList();
		return new ResponseEntity<>(doctorList,HttpStatus.OK);
	}
	
	//==============================================================================================
	//==============================================================================================
	//Clinic Service Controller  Methods
	
	@PostMapping("/create-clinic")
	public ResponseEntity<String> createClinic(@RequestBody ClinicDTO input){
		String resultMsg = clinicService.createClinic(input);
		return new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-clinic")
	public ResponseEntity<String> updateClinic(@RequestBody ClinicDTO input)throws ResourceNotFoundException{
		String resultMsg = clinicService.updateClinic(input);
		return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/clinic/{cid}")
	public ResponseEntity<ClinicDTO> getClinicById(@PathVariable("cid") Long clinicId)throws ResourceNotFoundException{
		ClinicDTO cdetails = clinicService.getClinicById(clinicId);
		return new ResponseEntity<>(cdetails,HttpStatus.OK);
	}
	
	@GetMapping("/all-clinics")
	public ResponseEntity<List<ClinicDTO>> getAllClinics(){
		List<ClinicDTO> clinicList = clinicService.getAllClinic();
		return new ResponseEntity<>(clinicList,HttpStatus.OK);
	}
}
