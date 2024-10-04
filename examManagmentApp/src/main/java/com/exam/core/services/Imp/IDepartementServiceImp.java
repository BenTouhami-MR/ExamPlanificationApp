package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Departement;
import com.exam.core.dao.IDepartementRepositoryDao;
import com.exam.core.services.IDepartementService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IDepartementServiceImp implements IDepartementService {
		
		
		@Autowired
		private IDepartementRepositoryDao departementDao;

		
		
		
		public void addDepartement(Departement pDepartement) {
			
			departementDao.save(pDepartement);
		}
		
		
		public void updateDepartement(Departement pDepartement) {
			
			departementDao.save(pDepartement);		
		}
		
		public void deleteDepartement(Long id) {
			
			departementDao.deleteById(id);
		}
		
		
		public List<Departement> getAllDepartements(){
			
			
			return departementDao.findAll();
			
		}
		
		
		public Departement getDepartementById(Long id) {
			
			return departementDao.findById(id).get();
		}


		public Departement getDepartementByNom(String nomDepartement) {
			return departementDao.getDepartementByNomDepartement(nomDepartement);
		}


		
		
}
