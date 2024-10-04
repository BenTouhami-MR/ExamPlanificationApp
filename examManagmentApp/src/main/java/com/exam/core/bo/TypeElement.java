package com.exam.core.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TypeElement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTypeElm;
	
	private String type;

	public TypeElement(){

	}
	public TypeElement(Long idTypeElm, String type) {
		this.idTypeElm = idTypeElm;
		this.type = type;
	}

	public Long getIdTypeElm() {
		return idTypeElm;
	}

	public void setIdTypeElm(Long idTypeElm) {
		this.idTypeElm = idTypeElm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	
	
	
}


