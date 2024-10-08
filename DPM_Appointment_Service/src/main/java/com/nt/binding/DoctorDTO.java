package com.nt.binding;

import lombok.Data;

@Data
public class DoctorDTO {
	private Long doctorId;
	private String doctorName;
	private String phoneNumber;
	private String email;
	private String specialty;
    private Long clinicId;
}
