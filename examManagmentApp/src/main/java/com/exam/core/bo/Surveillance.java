package com.exam.core.bo;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Surveillance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSurveil;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cordonnateur")

	@JsonBackReference
	private Enseignant coordonnateur ;
	
	
	@ManyToMany(mappedBy ="surveillances")
	@JsonManagedReference
	private Set<Enseignant> surveillants  = new HashSet<Enseignant>() ;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_controleurAbs")

	@JsonBackReference
	private Administrateur controleurAbscance;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "id_salle")
	@JsonBackReference
	private Salle salle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "id_exam")
	@JsonBackReference
	private Exam exam;
	
	@Column(name ="nbre_surveillants")
	private int nbreSurvaillance ;

	public int getNbreSurvaillance() {
		return nbreSurvaillance;
	}

	public void setNbreSurvaillance(int nbreSurvaillance) {
		this.nbreSurvaillance = nbreSurvaillance;
	}



	public Exam getExam() {
		return exam;
	}



	public void setExam(Exam exam) {
		this.exam = exam;
		exam.getSurveillances().add(this);
	}



	public Salle getSalle() {
		return salle;
	}



	public void setSalle(Salle salle) {
		this.salle = salle;
		salle.getSurveillances().add(this);
		
	}



	public Long getIdSurveil() {
		return idSurveil;
	}



	public void setIdSurveil(Long idSurveil) {
		this.idSurveil = idSurveil;
	}



	public Enseignant getCoordonnateur() {
		return coordonnateur;
	}



	public void setCoordonnateur(Enseignant cordonnateur) {
		this.coordonnateur = cordonnateur;
		cordonnateur.getCoordinations().add(this);
	}



	public Set<Enseignant> getSurveillants() {
		return surveillants;
	}



	public void setSurveillants(Set<Enseignant> surveillants) {
		
		this.surveillants = surveillants;
		
		for (Enseignant s : surveillants) {
			s.getSurveillances().add(this);
		}
	}

	public void addSurveillant(Enseignant surveillant) {
		surveillants.add(surveillant);
		surveillant.getSurveillances().add(this);
	}

	public void removeSurveillant(Enseignant surveillant) {
		surveillants.remove(surveillant);
		surveillant.getSurveillances().remove(this);
	}



	public Administrateur getControleurAbscance() {
		return controleurAbscance;
	}



	public void setControleurAbscance(Administrateur controleurAbscance) {
		this.controleurAbscance = controleurAbscance;
		controleurAbscance.getSurveillances().add(this);
	}


	@Override
	public String toString() {
		return "Surveillance{" +
				"idSurveil=" + idSurveil +
				", coordonnateur=" + coordonnateur +
				", controleurAbscance=" + controleurAbscance +
				", salle=" + salle +
				", exam=" + exam +
				", nbreSurvaillance=" + nbreSurvaillance +
				'}';
	}
}
