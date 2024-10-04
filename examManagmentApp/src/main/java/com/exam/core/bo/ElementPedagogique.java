package com.exam.core.bo;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class ElementPedagogique {

	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdElmtPedag;
	
	@NotBlank
	private String titre;
	
	
	@ManyToOne
	@JoinColumn(name = "id_eneignant")
	private Enseignant enseignant;
	
	@ManyToOne
	@JoinColumn(name = "id_coordinateur")
	@NotNull(message = "ne doit pas être vide")
	private Enseignant coordinateur = enseignant;
	
	@ManyToOne
	@JoinColumn(name = "id_niveau")
	@NotNull(message = "ne doit pas être vide")
	@JsonBackReference
	private Niveau niveau;
	
	@ManyToOne
	@JoinColumn(name = "id_typeElement")
	@NotNull(message = "ne doit pas être vide")
	@JsonBackReference
	private TypeElement typeElement;
	
	
	@OneToMany(mappedBy = "elementPedagogique", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	@JsonManagedReference
	@JsonIgnore
	private Set<Exam> exams = new HashSet<Exam>();
	
	
	
	
	
	public Long getIdElmtPedag() {
		return IdElmtPedag;
	}


	public void setIdElmtPedag(Long idElmtPedag) {
		IdElmtPedag = idElmtPedag;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Enseignant getEnseignant() {
		return enseignant;
	}


	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;

	}


	public Enseignant getCoordinateur() {
		return coordinateur;
	}


	public void setCoordinateur(Enseignant coordinateur) {
		this.coordinateur = coordinateur;
	}


	public Niveau getNiveau() {
		return niveau;
	}


	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}


	public TypeElement getTypeElement() {
		return typeElement;
	}


	public void setTypeElement(TypeElement typeElement) {
		this.typeElement = typeElement;
	}


	public Set<Exam> getExams() {
		return exams;
	}


	public void setExams(Set<Exam> exams) {
		this.exams = exams;
		for (Exam e : exams) {
			e.setElementPedagogique(this);
		}
	}


	public void addExam(Exam exam) {
		exams.add(exam);
		exam.setElementPedagogique(this);
	}

	public void removeExam(Exam exam) {
		exams.remove(exam);
		exam.setElementPedagogique(null);
	}
	


	
	
	
}



