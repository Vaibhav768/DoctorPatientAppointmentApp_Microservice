package com.nt.service;

import java.util.List;

import com.nt.binding.DoctorDTO;
import com.nt.exception.ResourceNotFoundException;

public interface DoctorAPIService {
	public String createDoctor(DoctorDTO input) throws ResourceNotFoundException;
	public String updateDoctor(Long doctorId, DoctorDTO input) throws ResourceNotFoundException;
	public DoctorDTO getDoctorById(Long doctorId)throws ResourceNotFoundException;
	public List<DoctorDTO> getAllDoctorsList();
}
