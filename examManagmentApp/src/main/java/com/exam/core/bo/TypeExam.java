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
public class TypeExam {

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_type_exam")
	private Long idTypeExam;
	
	
	private String intitule;
	
	@OneToMany(mappedBy ="typeExam",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private Set<Exam> exams  = new HashSet<Exam>();
	

	public TypeExam(){

	}

	public TypeExam(Long idTypeExam, String intitule) {
		this.idTypeExam = idTypeExam;
		this.intitule = intitule;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
		for (Exam e : exams) {
			e.setTypeExam(this);
		}
	}


	public void addExam(Exam exam) {
		exams.add(exam);
		exam.setTypeExam(this);
	}

	public void removeExam(Exam exam) {
		exams.remove(exam);
		exam.setTypeExam(null);
	}


	public Long getIdTypeExam() {
		return idTypeExam;
	}


	public void setIdTypeExam(Long idTypeExam) {
		this.idTypeExam = idTypeExam;
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

	
}
