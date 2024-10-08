package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.PatientEntity;

public interface PatientAPIRepository extends JpaRepository<PatientEntity, Long> {

}
