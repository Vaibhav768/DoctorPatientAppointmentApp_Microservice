package com.nt.service;

import java.util.List;

import com.nt.binding.ClinicDTO;
import com.nt.exception.ResourceNotFoundException;

public interface ClinicAPIService {
	public String createClinic(ClinicDTO input);
	public String updateClinic( ClinicDTO input)throws ResourceNotFoundException;
	public ClinicDTO getClinicById(Long clinicId)throws ResourceNotFoundException;
	public List<ClinicDTO> getAllClinic();
}
