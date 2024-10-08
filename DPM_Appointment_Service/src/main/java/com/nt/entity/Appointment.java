package com.nt.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="APPOINTMENT_DETAILS")
public class Appointment {
	@Id
	@SequenceGenerator(name="gen4",sequenceName="Appointment_Id_Seq",initialValue=4000,allocationSize=1)
	@GeneratedValue(generator="gen4",strategy=GenerationType.AUTO)
	private Long appointmentId;
	@NonNull
	private Long doctorId;
	@NonNull
	private Long patientId;
	@NonNull
	private LocalDateTime appointmentDate;
	@NonNull
	private String appointmentStatus;
}
