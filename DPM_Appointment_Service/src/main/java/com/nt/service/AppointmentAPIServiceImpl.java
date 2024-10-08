package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.AppointmentDTO;
import com.nt.binding.DoctorDTO;
import com.nt.binding.PatientDTO;
import com.nt.clientcomp.DoctorServiceConsumer;
import com.nt.clientcomp.PatientServiceConsumer;
import com.nt.entity.Appointment;
import com.nt.exception.DoctorNotFoundException;
import com.nt.exception.PatientNotFoundException;
import com.nt.exception.ResourceNotFoundException;
import com.nt.repository.AppointmentAPIRepository;

@Service
public class AppointmentAPIServiceImpl implements AppointmentAPIService {

	@Autowired
	private AppointmentAPIRepository appointmentRepo;
	
	@Autowired
	private DoctorServiceConsumer doctorClient;
	
	@Autowired
	private PatientServiceConsumer patientClient;
	
	//create new appointment
	@Override
	public String createAppointment(AppointmentDTO input) {
				  // Verify if doctor exists
		        DoctorDTO doctor = doctorClient.getDoctorById(input.getDoctorId());
		        if (doctor == null) {
		            throw new DoctorNotFoundException("Doctor not found with ID: " + input.getDoctorId());
		        }
		        // Verify if patient exists
		        PatientDTO patient = patientClient.getPatientById(input.getPatientId());
		        if (patient == null) {
		            throw new PatientNotFoundException("Patient not found with ID: " + input.getPatientId());
		        }
		        
		        Appointment appointment = new Appointment();
		        appointment.setDoctorId(doctor.getDoctorId());
		        appointment.setPatientId(patient.getPatientId());
		        appointment.setAppointmentDate(input.getAppointmentDate());
		        appointment.setAppointmentStatus("Scheduled");
		
		        Appointment appointmentDetails = appointmentRepo.save(appointment);
		
		        return "Appointment is scheduled with Appointment ID::"+appointmentDetails.getAppointmentId()+
		        		"for Patient ID::"+appointmentDetails.getPatientId();
	}

	//update existing appointment
	@Override
	public AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO input)throws Exception {
				  // Check if appointment exists
		        Appointment existingAppointment = appointmentRepo.findById(appointmentId)
		            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + appointmentId));
		
		        // Optionally update doctor if provided
		        if (input.getDoctorId() != null) {
		            DoctorDTO doctor = doctorClient.getDoctorById(input.getDoctorId());
		            if (doctor == null) {
		                throw new DoctorNotFoundException("Doctor not found with ID: " + input.getDoctorId());
		            }
		            existingAppointment.setDoctorId(doctor.getDoctorId());
		        }
		
		        // Optionally update patient if provided
		        if (input.getPatientId() != null) {
		            PatientDTO patient = patientClient.getPatientById(input.getPatientId());
		            if (patient == null) {
		                throw new PatientNotFoundException("Patient not found with ID: " + input.getPatientId());
		            }
		            existingAppointment.setPatientId(patient.getPatientId());
		        }
		
		        // Update appointment time if provided
		        if (input.getAppointmentDate() != null) {
		            existingAppointment.setAppointmentDate(input.getAppointmentDate());
		        }
		
		        // Update status if provided
		        if (input.getAppointmentStatus() != null) {
		            existingAppointment.setAppointmentStatus(input.getAppointmentStatus());
		        }
		
		        // Save updated appointment to the repository
		        existingAppointment = appointmentRepo.save(existingAppointment);
		        
		        // Return updated appointment DTO
		        input.setAppointmentId(existingAppointment.getAppointmentId());
		        input.setDoctorId(existingAppointment.getDoctorId());
		        input.setPatientId(existingAppointment.getPatientId());
		        input.setAppointmentDate(existingAppointment.getAppointmentDate());
		        input.setAppointmentStatus(existingAppointment.getAppointmentStatus());
		
		        return input;
	}

	//Get appointment details by Appointment ID
	@Override
	public AppointmentDTO getAppointDetailsById(Long appointmentId) {
				// Fetch the appointment by appointmentId, throw exception if not found
				Appointment appointmentEntity = appointmentRepo.findById(appointmentId).orElseThrow(()->new IllegalArgumentException("Appointment not found.."));
				
				// Create a new AppointmentDTO and copy properties from entity
				AppointmentDTO appointment = new AppointmentDTO();
				BeanUtils.copyProperties(appointmentEntity,appointment);
				
				// Return the DTO with appointment details
				return appointment;
	}

	//Get all Appointment
	@Override
	public List<AppointmentDTO> getAllAppointments() {
		// Fetch all appointments from the repository
				 List<Appointment> appointments = appointmentRepo.findAll();
				 
				// Create an empty list to store appointment DTOs
				 List<AppointmentDTO> appointmentList = new ArrayList<>();
				 
				// Convert each Appointment entity to AppointmentDTO and add it to the list
				 appointments.forEach(appointment->{
					    AppointmentDTO appointmentDTO = new AppointmentDTO();
						BeanUtils.copyProperties(appointment, appointmentDTO);
						appointmentList.add(appointmentDTO);
					});
				 
				// Return the list of appointment DTOs
				 return appointmentList;
	}
	
}
