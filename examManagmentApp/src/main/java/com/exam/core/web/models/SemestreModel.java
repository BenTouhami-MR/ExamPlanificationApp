package com.exam.core.web.models;

import com.exam.core.bo.Semestre;
import org.springframework.beans.BeanUtils;

public class SemestreModel {



	private Long idSemestre;
	
	private String intitule;
	
	public SemestreModel(Semestre s){
		BeanUtils.copyProperties(s,this);
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



	
}
