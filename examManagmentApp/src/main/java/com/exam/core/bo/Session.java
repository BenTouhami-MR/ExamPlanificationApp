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

@Entity
public class Session {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSession;
	
	
	private String intitule;
	
	@OneToMany(mappedBy ="session",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private Set<Exam> exams  = new HashSet<Exam>();

	public Session(){

	}

	public Session(Long idSession, String intitule) {
		this.idSession = idSession;
		this.intitule = intitule;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
		for (Exam e : exams) {
			e.setSession(this);
		}
	}


	public void addExam(Exam exam) {
		exams.add(exam);
		exam.setSession(this);
	}

	public void removeExam(Exam exam) {
		exams.remove(exam);
		exam.setSession(null);
	}


	public Long getIdSession() {
		return idSession;
	}


	public void setIdSession(Long idSession) {
		this.idSession = idSession;
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
