package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Surveillance;
import com.exam.core.dao.ISurveillanceRepositoryDao;
import com.exam.core.services.ISurveillanceService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ISurveillanceServiceImp implements ISurveillanceService {


	@Autowired
	private ISurveillanceRepositoryDao surveillanceDao;
	
	
	
	
	
	public void addSurveillance(Surveillance pSurveillance) {
		
		surveillanceDao.save(pSurveillance);
		
	}
	
	
	public void updateSurveillance(Surveillance pSurveillance) {
		
		surveillanceDao.save(pSurveillance);
		
	}
	
	public void deleteSurveillance(Long id) {
		
		surveillanceDao.deleteById(id);
	}
	
	
	public List<Surveillance> getAllSurveillances(){
		
		
		return surveillanceDao.findAll();
		
	}
	
	
	public Surveillance getSurveillanceById(Long id) {
		
		return surveillanceDao.findById(id).get();
	}
	
}
