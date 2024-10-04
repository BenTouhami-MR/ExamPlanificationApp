package com.exam.core.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Niveau {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_niv")
	private Long idNiv;
	
	
	private String  type;

	public Niveau(){

	}

	public Niveau(Long idNiv, String type) {
		this.idNiv = idNiv;
		this.type = type;
	}

	public Long getIdNiv() {
		return idNiv;
	}


	public void setIdNiv(Long idNiv) {
		this.idNiv = idNiv;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
}
