package com.exam.core.bo;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Administrateur extends Personnel {

	
	@OneToMany(mappedBy = "controleurAbscance" , cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})

	@JsonManagedReference
	private Set<Surveillance> surveillances = new HashSet<Surveillance>();

	public Set<Surveillance> getSurveillances() {
		return surveillances;
	}

	public void setSurveillances(Set<Surveillance> surveillances) {
		this.surveillances = surveillances;
		for (Surveillance s : surveillances) {
			s.setControleurAbscance(this);
		}
	}
	
	

	

	public void addSurveillance(Surveillance s) {
		surveillances.add(s);
		s.setControleurAbscance(this);
	}

	public void removeSurveillance(Surveillance s) {
		surveillances.remove(s);
		s.setControleurAbscance(null);
	}
	
	
}
