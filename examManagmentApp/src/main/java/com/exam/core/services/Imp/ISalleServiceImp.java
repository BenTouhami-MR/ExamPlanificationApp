package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Salle;
import com.exam.core.dao.ISalleRepositoryDao;
import com.exam.core.services.ISalleService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ISalleServiceImp implements ISalleService {

	
	@Autowired
	private ISalleRepositoryDao salleDao;
	
	
	
	
	
	public void addSalle(Salle pSalle) {
		
		salleDao.save(pSalle);
		
	}
	
	
	public void updateSalle(Salle pSalle) {
		
		salleDao.save(pSalle);
		
	}
	
	public void deleteSalle(Long id) {
		
		salleDao.deleteById(id);
	}
	
	
	public List<Salle> getAllSalles(){
		
		
		return salleDao.findAll();
		
	}
	
	
	public Salle getSalleById(Long id) {
		
		return salleDao.findById(id).get();
	}
	
}
