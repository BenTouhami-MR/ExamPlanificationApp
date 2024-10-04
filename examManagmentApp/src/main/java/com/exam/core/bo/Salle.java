package com.exam.core.bo;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Salle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSalle;
	
	@NotBlank
	private String nomSalle;
	
	
	private int capacite;
	
	
	@OneToMany(mappedBy="salle",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private Set<Surveillance> surveillances = new HashSet<Surveillance>();

	public Salle(){

	}
	public Salle(Long idSalle, String nomSalle, int capacite) {
		this.idSalle = idSalle;
		this.nomSalle = nomSalle;
		this.capacite = capacite;
	}

	public Long getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(Long idSalle) {
		this.idSalle = idSalle;
	}

	public String getNomSalle() {
		return nomSalle;
	}

	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public Set<Surveillance> getSurveillances() {
		return surveillances;
	}

	public void setSurveillances(Set<Surveillance> surveillances) {
		this.surveillances = surveillances;
	}

	public void setSurveillance(Set<Surveillance> surveillance) {
		this.surveillances = surveillance;
		for (Surveillance s :surveillance) {
			s.setSalle(this);;
		}
	}

	public void addSurveillance(Surveillance surveillance) {
		surveillances.add(surveillance);
		surveillance.setSalle(this);
	}

	public void removeSurveillance(Surveillance surveillance) {
		surveillances.remove(surveillance);
		surveillance.setSalle(null);
		}

	
}
