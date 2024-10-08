package com.nt.service;

import java.util.List;

import com.nt.binding.AppointmentDTO;

public interface AppointmentAPIService {
	public String createAppointment(AppointmentDTO input);
	public AppointmentDTO updateAppointment( Long appointmentId, AppointmentDTO input)throws Exception;
	public AppointmentDTO getAppointDetailsById(Long appointmentId);
	public List<AppointmentDTO> getAllAppointments();
}
