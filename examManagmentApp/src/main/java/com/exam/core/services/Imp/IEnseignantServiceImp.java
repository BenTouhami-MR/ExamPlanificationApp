package com.exam.core.services.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Departement;
import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Filiere;
import com.exam.core.dao.IEnseignantRepositoryDao;
import com.exam.core.services.IEnseignantService;

import jakarta.transaction.Transactional;

import java.util.List;



@Service
@Transactional
public class IEnseignantServiceImp  implements IEnseignantService{

	@Autowired
	private IEnseignantRepositoryDao enseignantDao;
	
	
	
	
	
	public void addEnseignant(Enseignant pEnseignant) {
		
		enseignantDao.save(pEnseignant);
		
	}
	
	
	public void updateEnseignant(Enseignant pEnseignant) {
		
		enseignantDao.save(pEnseignant);
		
	}
	
	public void deleteEnseignant(Long id) {
		
		enseignantDao.deleteById(id);
	}
	
	
	public List<Enseignant> getAllEnseignants(){
		
		
		return enseignantDao.findAll();
		
	}
	
	
	public Enseignant getEnseignantById(Long id) {
		
		return enseignantDao.findById(id).get();
	}
	
	
	public List<Enseignant> getEnseignantByFiliere(Filiere filiere){
		
		return enseignantDao.getEnseignantsByFiliere(filiere);
	}
	
	public List<Enseignant> getEnseignantByDepartement(Departement departement){
		return enseignantDao.getEnseignantsByDepartement(departement);
	}
 
}
