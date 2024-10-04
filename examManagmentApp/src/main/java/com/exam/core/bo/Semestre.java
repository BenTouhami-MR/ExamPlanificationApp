package com.exam.core.bo;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Semestre {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_semestre")
	private Long idSemestre;
	
	private String intitule;
	
	@OneToMany(mappedBy ="semestre",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private Set<Exam> exams  = new HashSet<Exam>();

	public Semestre(){


	}
	public Semestre(Long idSemestre, String intitule) {
		this.idSemestre = idSemestre;
		this.intitule = intitule;
	}

	public Long getIdSemestre() {
		return idSemestre;
	}


	public void setIdSemestre(Long idSemestre) {
		this.idSemestre = idSemestre;
	}


	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public Set<Exam> getExams() {
		return exams;
	}


	
	public void setExams(Set<Exam> exams) {
		this.exams = exams;
		for (Exam e : exams) {
			e.setSemestre(this);
		}
	}


	public void addExam(Exam exam) {
		exams.add(exam);
		exam.setSemestre(this);
	}

	public void removeExam(Exam exam) {
		exams.remove(exam);
		exam.setSemestre(null);
	}

	
}
