package com.nt.binding;

import lombok.Data;

@Data
public class PatientDTO {
	private Long patientId;
	private String patientName;
	private String phoneNumber;
	private String email;
	private String disease;
	private Integer age;
}
