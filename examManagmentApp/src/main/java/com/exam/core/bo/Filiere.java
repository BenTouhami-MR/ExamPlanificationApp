package com.exam.core.bo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Filiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_fil")
	private Long idFil;
	
	private String nomFiliere;
	
	
	@OneToMany(mappedBy = "filiere" , cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Set<Enseignant> enseignants = new HashSet<Enseignant>();

	public Long getIdFil() {
		return idFil;
	}

	public void setIdFil(Long id_fil) {
		this.idFil = idFil;
	}

	public Set<Enseignant> getEnseignants() {
		return enseignants;
	}

	public Filiere(){

	}
	public Filiere(Long idFil, String nomFiliere) {
		this.idFil = idFil;
		this.nomFiliere = nomFiliere;
	}

	public void setEnseignants(Set<Enseignant> enseignants) {
		this.enseignants = enseignants;
		for (Enseignant ad : enseignants) {
			ad.setFiliere(this);

		}
	}

	public void addEnseignant(Enseignant ad) {
		enseignants.add(ad);
		ad.setFiliere(this);
	}

	public void removeEnseignant(Enseignant add) {
		enseignants.remove(add);
		add.setFiliere(null);
	}

	public String getNomFiliere() {
		return nomFiliere;
	}

	public void setNomFiliere(String nomFiliere) {
		this.nomFiliere = nomFiliere;
	}

	@Override
	public String toString() {
		return "Filiere [idFil=" + idFil + ", nomFiliere=" + nomFiliere + ", enseignants=" + enseignants + "]";
	}
	
	
	
}
