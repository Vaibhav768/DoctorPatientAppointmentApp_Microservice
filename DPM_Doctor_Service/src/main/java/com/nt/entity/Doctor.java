package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="DOCTOR_DETAILS")
public class Doctor {
	@Id
	@SequenceGenerator(name="gen2",sequenceName="Doctor_Id_Seq",initialValue=2000,allocationSize=1)
	@GeneratedValue(generator="gen2",strategy=GenerationType.AUTO)
	private Long doctorId;
	@NonNull
	private String doctorName;
	@NonNull
	private String phoneNumber;
	@NonNull
	private String email;
	@NonNull
	private String specialty;
	
	@ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
}
