package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.core.bo.Exam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IExamRepositoryDao extends JpaRepository<Exam, Long> {

    List<Exam> findExamByDate(LocalDate date);

}