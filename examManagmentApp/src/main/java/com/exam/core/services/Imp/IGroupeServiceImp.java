package com.exam.core.services.Imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.exam.core.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Groupe;
import com.exam.core.dao.IEnseignantRepositoryDao;
import com.exam.core.dao.IGroupeRepositoryDao;
import com.exam.core.services.IGroupeService;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class IGroupeServiceImp implements IGroupeService{

	
	
	@Autowired
	private IGroupeRepositoryDao groupeDao;


	@Autowired 
	private IEnseignantRepositoryDao enseignantDao;

	@Autowired
	private IPersonnelRepositoryDao persDao;
	
	private static Set<Enseignant>  enseignants = new HashSet<Enseignant>();

	public void createGroupe(Groupe groupe) {
		
		groupeDao.save(groupe);
		
	}
	
	
	public void updateGroupe(Groupe groupe) {
		
		groupeDao.save(groupe);
		
	}
	
	public void deleteGroupe(Long id) {

		Groupe groupe =getGroupeById(id);

		for (Enseignant e: groupe.getEnseignants()){
			e.setGroupe(null);
		}
		
		groupeDao.deleteById(id);
	}
	
	
	public List<Groupe> getAllGroupes(){
		
		
		return groupeDao.findAll();
		
	}
	
	
	public Groupe getGroupeById(Long id) {
		
		return groupeDao.findById(id).get();
	}
	
	
	
	public void ajouterUnEnseignant(Long idEnseignat,Long idGroupe) {
		
		Enseignant enseignant = enseignantDao.findById(idEnseignat).get();
		Groupe groupe  = getGroupeById(idGroupe);
		
		groupe.getEnseignants().add(enseignant);
		enseignant.setGroupe(groupe);


	}



	public void deleteEnseignantById(Long idGroupe, Long idEnseignant){
		Groupe g =getGroupeById(idGroupe);

		Enseignant e = enseignantDao.findById(idEnseignant).get();

		for (Enseignant es : g.getEnseignants()){

			if (es.getIdPersonnel()==idEnseignant){
				g.getEnseignants().remove(es);
				es.setGroupe(null);
				break;
			}
		}

		groupeDao.save(g);

	}




	


	

	
}