package com.nt.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name="PATIENT_DETAILS")
public class PatientEntity {
	@Id
	@SequenceGenerator(name="gen1",sequenceName="Patient_Id_Seq",initialValue=1000,allocationSize=1)
	@GeneratedValue(generator="gen1",strategy=GenerationType.AUTO)
	private Long patientId;
	@NonNull
	private String patientName;
	@NonNull
	private String phoneNumber;
	@NonNull
	private String email;
	@NonNull
	private String disease;
	@NonNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dob;
	@NonNull
	private Integer age;
}
