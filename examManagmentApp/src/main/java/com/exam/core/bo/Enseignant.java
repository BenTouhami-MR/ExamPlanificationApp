package com.exam.core.bo;

import java.util.HashSet;
import java.util.Set;


import com.exam.core.web.models.GroupeModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Enseignant extends Personnel{

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_filiere")
	@JsonBackReference
	private Filiere filiere;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dep")
	@JsonBackReference
	private Departement departement;
	
	
	@OneToMany(mappedBy ="coordonnateur",  cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})

	@JsonManagedReference

	private Set<Surveillance> coordinations =  new HashSet<Surveillance>();
	
	
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
	@JoinTable(name = "surveillant_surveillance_tab",
			joinColumns = @JoinColumn(name = "id_survaillant"),
			inverseJoinColumns = @JoinColumn(name = "id_survaillance"))
	@JsonIgnore
	private Set<Surveillance> surveillances =  new HashSet<Surveillance>();

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Groupe groupe;


	public Filiere getFiliere() {
		return filiere;
	}



	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
		filiere.getEnseignants().add(this);
	}



	public Departement getDepartement() {
		return departement;
	}



	public void setDepartement(Departement departement) {
		this.departement = departement;
		departement.getEnseignants().add(this);
	}



	public Set<Surveillance> getCoordinations() {
		return coordinations;
	}



	public void setCoordinations(Set<Surveillance> coordinations) {
		this.coordinations = coordinations;
		for (Surveillance c:coordinations) {
			c.setCoordonnateur(this);
		}
	}
	
	public void addCoordinatoins(Surveillance coordination) {
		coordinations.add(coordination);
		coordination.setCoordonnateur(this);
	}

	public void removeEnseignant(Surveillance coordination) {
		coordinations.remove(coordination);
		coordination.setCoordonnateur(null);
	}



	public Set<Surveillance> getSurveillances() {
		return surveillances;
	}



	public void setSurveillances(Set<Surveillance> surveillances) {
		this.surveillances = surveillances;
		for (Surveillance s : surveillances) {
			s.getSurveillants().add(this);
		}
	}


	public void addSurveillance(Surveillance s) {
		surveillances.add(s);
		s.getSurveillants().add(this);
	}
	

	public void removeSurveillance(Surveillance s) {
		surveillances.remove(s);
		s.getSurveillants().remove(this);
	}


	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
}
