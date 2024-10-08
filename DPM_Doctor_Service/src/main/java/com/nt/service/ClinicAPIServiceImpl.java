package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.ClinicDTO;
import com.nt.entity.Clinic;
import com.nt.exception.ResourceNotFoundException;
import com.nt.repository.ClinicAPIRepository;

@Service
public class ClinicAPIServiceImpl implements ClinicAPIService {

	@Autowired
	private ClinicAPIRepository clinicRepo;
	
	@Override
	public String createClinic(ClinicDTO input) {
		Clinic clinic = new Clinic();
		//copying the input DTO properties to the entity
		BeanUtils.copyProperties(input, clinic);
        Long clinicId = clinicRepo.save(clinic).getClinicId();
        return "Clinic is saved with Clinic Id::"+clinicId;
	}

	@Override
	public String updateClinic(ClinicDTO input) throws ResourceNotFoundException {
		// Find the clinic by its ID
		Optional<Clinic> optional = clinicRepo.findById(input.getClinicId());
		if(optional.isPresent()) {
			Clinic clinic = new Clinic();
			//copying the input DTO properties to the entity
			BeanUtils.copyProperties(input, clinic);
			clinicRepo.save(clinic);
			return "Clinic details for clinicId:: "+input.getClinicId()+" are updated successfully.";
		}
		return "Clinic Not Found";
	}

	@Override
	public ClinicDTO getClinicById(Long clinicId) throws ResourceNotFoundException{
		ClinicDTO patientDetails = new ClinicDTO();
		// Fetch the clinic by ID
		Clinic clinic = clinicRepo.findById(clinicId).orElseThrow(()->new ResourceNotFoundException("Clinic not found.."));
		BeanUtils.copyProperties(clinic, patientDetails);
		return patientDetails;
	}

	@Override
	public List<ClinicDTO> getAllClinic() {
		// Retrieve all clinics from the repository
		List<Clinic> clinics = clinicRepo.findAll();
		List<ClinicDTO> clinicList = new ArrayList<>();
		// Convert each clinic entity to DTO
		clinics.forEach(clinic->{
			ClinicDTO clinicDTO = new ClinicDTO();
			BeanUtils.copyProperties(clinic, clinicDTO);
			clinicList.add(clinicDTO);
		});
	 return clinicList;
	}

}
