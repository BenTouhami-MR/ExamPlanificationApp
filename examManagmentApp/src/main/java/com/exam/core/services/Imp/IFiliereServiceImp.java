package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Filiere;
import com.exam.core.dao.IFiliereRepositoryDao;
import com.exam.core.services.IFiliereService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class IFiliereServiceImp  implements IFiliereService{
	
	
	@Autowired
	private IFiliereRepositoryDao filiereDao;
	
	
	
	
	
	public void addFiliere(Filiere pFiliere) {
		
		filiereDao.save(pFiliere);
		
	}
	
	
	public void updateFiliere(Filiere pFiliere) {
		
		filiereDao.save(pFiliere);
		
	}
	
	public void deleteFiliere(Long id) {
		
		filiereDao.deleteById(id);
	}
	
	
	public List<Filiere> getAllFilieres(){
		
		
		return filiereDao.findAll();
		
	}
	
	
	public Filiere getFiliereById(Long id) {
		
		return filiereDao.findById(id).get();
	}
	

}
