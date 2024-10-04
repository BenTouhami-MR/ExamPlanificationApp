package com.exam.core.bo;

import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_dep")
	private Long idDep;
	
	private String nomDepartement;
	

	@OneToMany(mappedBy = "departement", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonManagedReference

	private Set<Enseignant> enseignants = new HashSet<Enseignant>();

	public Departement(){

	}

	public Departement(Long idDep, String nomDepartement) {
		this.idDep = idDep;
		this.nomDepartement = nomDepartement;
	}

	public String getNomDepartement() {
		return nomDepartement;
	}


	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}


	public Long getIdDep() {
		return idDep;
	}


	public void setIdDep(Long idDep) {
		this.idDep = idDep;
	}


	public Set<Enseignant> getEnseignants() {
		return enseignants;
	}



	
	public void setEnseignants(Set<Enseignant> enseignants) {
		this.enseignants = enseignants;
		for (Enseignant ad : enseignants) {
			ad.setDepartement(this);

		}
	}

	public void addEnseignant(Enseignant ad) {
		enseignants.add(ad);
		ad.setDepartement(this);
	}

	public void removeEnseignant(Enseignant add) {
		enseignants.remove(add);
		add.setDepartement(null);
	}


	@Override
	public String toString() {
		return "Departement [idDep=" + idDep + ", nomDepartement=" + nomDepartement + ", enseignants=" + enseignants
				+ "]";
	}

	
}
