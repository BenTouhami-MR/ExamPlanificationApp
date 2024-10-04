package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Semestre;
import com.exam.core.dao.ISemestreRepositoryDao;
import com.exam.core.services.ISemestreService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ISemestreServiceImp implements ISemestreService{
	
	
	@Autowired
	private ISemestreRepositoryDao semestreDao;
	
	
	
	
	
	public void addSemestre(Semestre pSemestre) {
		
		semestreDao.save(pSemestre);
		
	}
	
	
	public void updateSemestre(Semestre pSemestre) {
		
		semestreDao.save(pSemestre);
		
	}
	
	public void deleteSemestre(Long id) {
		
		semestreDao.deleteById(id);
	}
	
	
	public List<Semestre> getAllSemestres(){
		
		
		return semestreDao.findAll();
		
	}
	
	
	public Semestre getSemestreById(Long id) {
		
		return semestreDao.findById(id).get();
	}
	

}
