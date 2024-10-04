package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.core.bo.Groupe;

public interface IGroupeRepositoryDao extends JpaRepository<Groupe, Long>{

	
	Groupe getGroupeByNomGroupe(String nomGroupe);
}
