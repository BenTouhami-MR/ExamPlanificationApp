package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.core.bo.Session;

public interface ISessionRepositoryDao extends JpaRepository<Session, Long>{

}
