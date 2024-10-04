package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.core.bo.Salle;

public interface ISalleRepositoryDao extends JpaRepository<Salle, Long> {

}
