package com.exam.core.bo;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;

import java.io.Serializable;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Personnel implements Serializable {
	
	
	@Id	
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "Id")
	private Long idPersonnel;
	
	
	@NotBlank
	private String nom;
	
	
	@NotBlank
	private String prenom;
	
	
	public Long getIdPersonnel() {
		return idPersonnel;
	}


	public void setIdPersonnel(Long idPersonnel) {
		this.idPersonnel = idPersonnel;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}





	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	@Override
	public String toString() {
		return "Personnel [Id=" + idPersonnel + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

}
