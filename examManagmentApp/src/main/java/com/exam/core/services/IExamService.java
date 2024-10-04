package com.exam.core.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.exam.core.bo.Exam;
import com.exam.core.web.models.ExamModel;
import com.exam.core.web.models.ExamModelReceiver;

public interface IExamService {

	public void addExam(ExamModelReceiver examModel);

	public void updateExam(Exam pExam);

	public List<Exam> getAllExams();

	public void deleteExam(Long id);

	public Exam getExamById(Long id);

	public List<Exam> getExamByDate(LocalDate data);

}
