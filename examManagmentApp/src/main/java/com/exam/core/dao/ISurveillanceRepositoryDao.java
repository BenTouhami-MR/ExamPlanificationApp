package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.core.bo.Exam;
import com.exam.core.bo.Surveillance;

public interface ISurveillanceRepositoryDao extends JpaRepository<Surveillance, Long>{

	
}
