package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Personnel;
import com.exam.core.dao.IPersonnelRepositoryDao;
import com.exam.core.services.IPersonnelService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class IPersonneServiceImp implements IPersonnelService {
	
	@Autowired
	private IPersonnelRepositoryDao personnelDao;

	
	
	public void addPersonnel(Personnel pPersonnel) {
		
		personnelDao.save(pPersonnel);
		
	
		
	}
	
	
	public void updatePersonnel(Personnel pPersonnel) {
		
		personnelDao.save(pPersonnel);

		
	}
	
	public void deletePersonnel(Long id) {


		personnelDao.deleteById(id);
	}
	
	
	public List<Personnel> getAllPersonnels(){
		
		
		return personnelDao.findAll();
		
	}
	
	
	public Personnel getPersonnelById(Long id) {
		
		return personnelDao.findById(id).get();
	}
	
}
