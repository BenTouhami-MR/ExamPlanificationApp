package com.exam.core.services;


import com.exam.core.bo.Groupe;
import java.util.List;

public interface IGroupeService {

	
	public void createGroupe(Groupe groupe);

	public void updateGroupe(Groupe groupe);

	public List<Groupe> getAllGroupes();

	public void deleteGroupe(Long id);

	public Groupe getGroupeById(Long id);
	
	public void ajouterUnEnseignant(Long idEnseignat, Long idGroupe);

	public void deleteEnseignantById(Long idGroupe, Long idEnseignant);
		
}
