package com.nt.clientcomp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.binding.DoctorDTO;

@FeignClient(name="DPM_Doctor_Service")
public interface DoctorServiceConsumer {
	
	@GetMapping("/doctorClinic-api/doctor/{did}")
    public DoctorDTO getDoctorById(@PathVariable Long did);
	
}
