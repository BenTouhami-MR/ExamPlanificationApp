package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Niveau;
import com.exam.core.dao.INiveauRepositoryDao;
import com.exam.core.services.INiveauService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class INiveauServiceImp implements INiveauService {

	@Autowired
	private INiveauRepositoryDao niveauDao;
	
   public List<Niveau> getAllNivaux(){
		
		
		return niveauDao.findAll();
		
	}
	
	
	public Niveau getNiveauById(Long id) {
		
		return niveauDao.findById(id).get();
	}
}
