package com.exam.core.web.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.exam.core.bo.Departement;
import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Filiere;

import com.exam.core.bo.Groupe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

public class GroupeModel {
	

	
	
	public GroupeModel() {
		
	}
		

	private Long idGroupe;
	
	private String groupeType;

	private Long idGroupeType;


	private String nomGroupe;
	
	private List<Long> enseignantsIds =new ArrayList<>();
	
	private Set<EnseignantModel> enseignants = new HashSet<>();



	public GroupeModel(Groupe groupe){

		BeanUtils.copyProperties(groupe,this);

		for (Enseignant e: groupe.getEnseignants()){

			EnseignantModel em = new EnseignantModel(e);
			this.enseignants.add(em);
		}

	}

	public Long getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(Long idGroupe) {
		this.idGroupe = idGroupe;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}


	public String getGroupeType() {
		return groupeType;
	}



	public Set<EnseignantModel> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(Set<EnseignantModel> enseignants) {
		this.enseignants = enseignants;
	}

	public void setGroupeType(String groupeType) {
		this.groupeType = groupeType;
	}

	public Long getIdGroupeType() {
		return idGroupeType;
	}
	public void setIdGroupeType(Long idGroupeType) {
		this.idGroupeType = idGroupeType;
	}

	public List<Long> getEnseignantsIds() {
		return enseignantsIds;
	}

	public void setEnseignantsIds(List<Long> enseignantsIds) {
		this.enseignantsIds = enseignantsIds;
	}
}
