package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Doctor;

public interface DoctorAPIRepository extends JpaRepository<Doctor, Long> {
	
}
