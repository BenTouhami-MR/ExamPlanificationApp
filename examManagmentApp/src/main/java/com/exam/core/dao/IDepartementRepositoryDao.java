package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.core.bo.Departement;

public interface IDepartementRepositoryDao extends JpaRepository<Departement, Long>{

	
	Departement getDepartementByNomDepartement(String nomDepartement);
}
