package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.TypeExam;
import com.exam.core.dao.ITypeExamRepositoryDao;
import com.exam.core.services.ITypeExamService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ITypeExamServiceImp implements ITypeExamService {
	
	@Autowired
	private ITypeExamRepositoryDao typeExamDao;
	
	public List<TypeExam> getAllTypeExams(){
		
		
		return typeExamDao.findAll();
		
	}
	
	


	public TypeExam getTypeExamById(Long id) {
		return  typeExamDao.findById(id).get();
	}
}
