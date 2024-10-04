package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Departement;
import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Filiere;

public interface IEnseignantService  {
		
	 void addEnseignant(Enseignant pEnseignant);

	 void updateEnseignant(Enseignant pEnseignant);

	 List<Enseignant> getAllEnseignants();

	 void deleteEnseignant(Long id);

	 Enseignant getEnseignantById(Long id);
	
	List<Enseignant> getEnseignantByFiliere(Filiere filiere);

	List<Enseignant> getEnseignantByDepartement(Departement departement);
}
