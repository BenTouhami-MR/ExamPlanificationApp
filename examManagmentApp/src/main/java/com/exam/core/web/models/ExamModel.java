package com.exam.core.web.models;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import com.exam.core.bo.*;

import jakarta.validation.constraints.NotNull;

public class ExamModel {

	
	public static final Long TYPE_MODULE=1L;
	public static final Long TYPE_ELEMENT=2L;

	
	private Long IdExam;
	


	

	@NotNull
	private TypeExamModel typeExam;

	private LocalDate date;
	private LocalTime heureDebut;

	private int dureePrevue;

	private SessionModel session;

	private SemestreModel semestre;

	
	private int durreeReelle;
	
	private String anneeUniversitaire;
	
	private String epreuve;

	private String PV;
	

	private String rapportTextuelle = "Rien à signaler";

	private ElementPedagogiqueModel elementPedagogique;
	

	

	
	private List<SalleModel> salles =new ArrayList<>();
	
	private EnseignantModel cordonnateur;
	
	
	
	
	public Long getIdExam() {
		return IdExam;
	}

	public void setIdExam(Long idExam) {
		IdExam = idExam;
	}

	public TypeExamModel getTypeExam() {
		return typeExam;
	}

	public void setTypeExam(TypeExamModel typeExam) {
		this.typeExam = typeExam;
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

	public ElementPedagogiqueModel getElementPedagogique() {
		return elementPedagogique;
	}

	public void setElementPedagogique(ElementPedagogiqueModel elementPedagogique) {
		this.elementPedagogique = elementPedagogique;
	}


	public List<SalleModel> getSalles() {
		return salles;
	}

	public void setSalles(List<SalleModel> salles) {
		this.salles = salles;
	}

	public EnseignantModel getCordonnateur() {
		return cordonnateur;
	}

	public void setCordonnateur(EnseignantModel cordonnateur) {
		this.cordonnateur = cordonnateur;
	}

	public SessionModel getSession() {
		return session;
	}

	public void setSession(SessionModel session) {
		this.session = session;
	}

	public SemestreModel getSemestre() {
		return semestre;
	}

	public void setSemestre(SemestreModel semestre) {
		this.semestre = semestre;
	}
	
	
	
	
	
	
}
