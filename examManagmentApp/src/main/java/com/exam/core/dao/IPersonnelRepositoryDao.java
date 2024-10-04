package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.core.bo.Personnel;

public interface IPersonnelRepositoryDao extends JpaRepository<Personnel, Long> {

}
