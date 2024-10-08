package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.DoctorDTO;
import com.nt.entity.Clinic;
import com.nt.entity.Doctor;
import com.nt.exception.ResourceNotFoundException;
import com.nt.repository.ClinicAPIRepository;
import com.nt.repository.DoctorAPIRepository;

@Service
public class DoctorAPIServiceImpl implements DoctorAPIService {

	@Autowired
	private DoctorAPIRepository doctorRepo;
	
	@Autowired
	private ClinicAPIRepository clinicRepo;
	
	@Override
	public String createDoctor(DoctorDTO input) throws ResourceNotFoundException {
		 	Doctor doctor = new Doctor();
	        doctor.setDoctorName(input.getDoctorName());
	        doctor.setPhoneNumber(input.getPhoneNumber());
	        doctor.setEmail(input.getEmail());
	        doctor.setSpecialty(input.getSpecialty());
	        
	        Clinic clinic = clinicRepo.findById(input.getClinicId())
	            .orElseThrow(() -> new ResourceNotFoundException("Clinic not found"));
	        doctor.setClinic(clinic);

	        Doctor doctorData= doctorRepo.save(doctor);

	        return "Doctor Dr. "+doctorData.getDoctorName()+" saved successfully with Doctor ID :: "+doctorData.getDoctorId()+
	        				" associated with clinic :: "+doctorData.getClinic().getClinicName();
	}

	@Override
	public String updateDoctor(Long doctorId, DoctorDTO input) throws  ResourceNotFoundException{
		Doctor doctor = doctorRepo.findById(doctorId)
	            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
	        
	        doctor.setDoctorName(input.getDoctorName());
	        doctor.setPhoneNumber(input.getPhoneNumber());
	        doctor.setEmail(input.getEmail());
	        doctor.setSpecialty(input.getSpecialty());

	        Clinic clinic = clinicRepo.findById(input.getClinicId())
	            .orElseThrow(() -> new ResourceNotFoundException("Clinic not found"));
	        doctor.setClinic(clinic);

	        Doctor doctorData = doctorRepo.save(doctor);
	        
	        return "Doctor doctorId:: "+doctorData.getDoctorId()+" is updated successfully." ;
		
	}

	@Override
	public DoctorDTO getDoctorById(Long doctorId)throws ResourceNotFoundException  {
		Doctor optional = doctorRepo.findById(doctorId).orElseThrow(()->new ResourceNotFoundException("Doctor not found.."));
		DoctorDTO doctorDto = new DoctorDTO();
		BeanUtils.copyProperties(optional,doctorDto);
		return doctorDto;
	}

	@Override
	public List<DoctorDTO> getAllDoctorsList() {
		List<Doctor> doctors = doctorRepo.findAll();
		List<DoctorDTO> doctorList = new ArrayList<>();
		doctors.forEach(doctor->{
					 DoctorDTO doctorDTO = new DoctorDTO();
					 BeanUtils.copyProperties(doctor, doctorDTO);
					 doctorList.add(doctorDTO);
					});
				 return doctorList;
	}

	
}
