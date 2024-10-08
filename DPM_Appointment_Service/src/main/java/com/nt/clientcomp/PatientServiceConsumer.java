package com.nt.clientcomp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.binding.PatientDTO;

@FeignClient(name="DPM_Patient_Service")
public interface PatientServiceConsumer {
	@GetMapping("/patient-api/patient/{pid}")
    public PatientDTO getPatientById(@PathVariable Long pid);
}
