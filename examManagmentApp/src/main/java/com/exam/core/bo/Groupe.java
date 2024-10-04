package com.exam.core.bo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;

@Entity

public class Groupe {

	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="id_Groupe")
	private Long idGroupe;

	private String nomGroupe;

	private Long idGroupeType;

	private String groupeType;
	
//	 orphanRemoval = true ==>  si je supprime un groupe donc tout les enseignants de ce groupe seront supprimés
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_groupe")
	@JsonManagedReference
	private Set<Enseignant> enseignants  =  new HashSet<Enseignant>();



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


	public Set<Enseignant> getEnseignants() {
		return enseignants;
	}


	public void setEnseignants(Set<Enseignant> enseignants) {
		for (Enseignant e: enseignants){

			e.setGroupe(this);
		}
		this.enseignants = enseignants;

	}

	public String getGroupeType() {
		return groupeType;
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

	@Override
	public String toString() {
		return "Groupe [id_Groupe=" + idGroupe + ", NomGroupe=" + nomGroupe +" ]";
	}
	
	
	
	
}
