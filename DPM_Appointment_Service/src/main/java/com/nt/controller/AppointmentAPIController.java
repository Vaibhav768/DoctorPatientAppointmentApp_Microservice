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

import com.nt.binding.AppointmentDTO;
import com.nt.exception.ResourceNotFoundException;
import com.nt.service.AppointmentAPIService;

@RestController
@RequestMapping("/appointment-api")
public class AppointmentAPIController {
	
	@Autowired
	private AppointmentAPIService appointmetService;
	
	@PostMapping("/create-appointment")
	public ResponseEntity<String> createAppointment(@RequestBody AppointmentDTO input)throws Exception{
		String resultMsg = appointmetService.createAppointment(input);
		return new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-appointment/{aid}")
	public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable("aid") Long appointmentId,@RequestBody AppointmentDTO input)throws Exception{
		AppointmentDTO resultMsg = appointmetService.updateAppointment(appointmentId,input);
		return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/appointment/{aid}")
	public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("aid") Long appointmentId)throws ResourceNotFoundException{
		AppointmentDTO adetails = appointmetService.getAppointDetailsById(appointmentId);
		return new ResponseEntity<>(adetails,HttpStatus.OK);
	}
	
	@GetMapping("/all-appointments")
	public ResponseEntity<List<AppointmentDTO>> getAllAppointment()throws IllegalArgumentException{
		List<AppointmentDTO> appointmentList = appointmetService.getAllAppointments();
		return new ResponseEntity<>(appointmentList,HttpStatus.OK);
	}
}
