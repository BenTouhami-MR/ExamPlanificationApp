package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.TypeExam;


public interface ITypeExamService {
	public TypeExam getTypeExamById(Long id);
	public List<TypeExam> getAllTypeExams();

}
