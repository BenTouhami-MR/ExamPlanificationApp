package com.exam.core.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Exam")
	private Long IdExam;
	
	
	private LocalDate date;
	private LocalTime heureDebut;
	
	private int dureePrevue;
	
	
	private int durreeReelle;
	
	@NotBlank
	private String anneeUniversitaire;
	
	@NotBlank
	private String epreuve;
	
	private String PV;
	
	@NotBlank
	private String rapportTextuelle = "Rien à signaler";
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private ElementPedagogique elementPedagogique;
	
	


	@OneToMany(mappedBy="exam",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	@JsonManagedReference
	private Set<Surveillance> surveillances = new HashSet<Surveillance>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Session session;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Semestre semestre;

	
	

	@ManyToOne(fetch = FetchType.LAZY)
	private TypeExam typeExam;
	
	
	
	
	public ElementPedagogique getElementPedagogique() {
		return elementPedagogique;
	}


	public void setElementPedagogique(ElementPedagogique elementPedagogique) {
		this.elementPedagogique = elementPedagogique;
		elementPedagogique.getExams().add(this);
	}
	public Semestre getSemestre() {
		return semestre;
	}


	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
		semestre.getExams().add(this);
	}


	public TypeExam getTypeExam() {
		return typeExam;
	}


	public void setTypeExam(TypeExam typeExam) {
		this.typeExam = typeExam;
		typeExam.getExams().add(this);
	}




	public Long getIdExam() {
		return IdExam;
	}


	public void setIdExam(Long IdExam) {
		this.IdExam = IdExam;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getHeureDebut() {
		return heureDebut;
	}


	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}


	public int getDureePrevue() {
		return dureePrevue;
	}


	public void setDureePrevue(int dureePrevue) {
		this.dureePrevue = dureePrevue;
	}


	public int getDurreeReelle() {
		return durreeReelle;
	}


	public void setDurreeReelle(int durreeReelle) {
		this.durreeReelle = durreeReelle;
	}


	public String getAnneeUniversitaire() {
		return anneeUniversitaire;
	}


	public void setAnneeUniversitaire(String anneeUniversitaire) {
		this.anneeUniversitaire = anneeUniversitaire;
	}


	public String getEpreuve() {
		return epreuve;
	}


	public void setEpreuve(String epreuve) {
		this.epreuve = epreuve;
	}


	public String getPV() {
		return PV;
	}


	public void setPV(String pV) {
		PV = pV;
	}


	public String getRapportTextuelle() {
		return rapportTextuelle;
	}


	public void setRapportTextuelle(String rapportTextuelle) {
		this.rapportTextuelle = rapportTextuelle;
	}

	public void setSurveillances(Set<Surveillance> surveillances) {
		this.surveillances = surveillances;
		for (Surveillance s : surveillances) {
			s.setExam(this);
		}
	}


	public void addSurveillance(Surveillance s) {
		surveillances.add(s);
		s.setExam(this);
	}

	public void removeSurveillance(Surveillance s) {
		surveillances.remove(s);
		s.setExam(null);
	}

	public Set<Surveillance> getSurveillances() {
		return surveillances;
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
		session.getExams().add(this);
	}

	@Override
	public String toString() {
		return "Exam{" +
				"IdExam=" + IdExam +
				", date=" + date +
				", heureDebut=" + heureDebut +
				", dureePrevue=" + dureePrevue +
				", durreeReelle=" + durreeReelle +
				", anneeUniversitaire='" + anneeUniversitaire + '\'' +
				", epreuve='" + epreuve + '\'' +
				", PV='" + PV + '\'' +
				", rapportTextuelle='" + rapportTextuelle + '\'' +
				", elementPedagogique=" + elementPedagogique +
				", session=" + session +
				", semestre=" + semestre +
				", typeExam=" + typeExam +
				'}';
	}
}

