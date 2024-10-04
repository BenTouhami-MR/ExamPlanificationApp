package com.exam.core.web.models;

import java.util.HashSet;
import java.util.Set;

import com.exam.core.bo.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PersonnelModel {


	public static final int TYPE_ADMIN = 1;
	public static final int TYPE_ENSEIGN = 2;


	private Long idPersonnel;

	@NotBlank
	private String nom;


	@NotBlank
	private String prenom;


	private String nom_departement;
	private Long id_departement;

	private String nom_filiere;


	private Long id_filiere;

	private int typePersonnel;



	public PersonnelModel(Personnel p) {
		nom = p.getNom();
		prenom = p.getPrenom();
		idPersonnel = p.getIdPersonnel();
		if (p instanceof Enseignant) {
			id_departement = ((Enseignant) p).getDepartement().getIdDep();
			nom_departement = ((Enseignant) p).getDepartement().getNomDepartement();
			id_filiere = ((Enseignant) p).getFiliere().getIdFil();
			nom_filiere = ((Enseignant) p).getFiliere().getNomFiliere();

			typePersonnel = TYPE_ENSEIGN;
		} else {
			typePersonnel = TYPE_ADMIN;
		}

	}

	public PersonnelModel(int typePersonnel) {
		this.typePersonnel = typePersonnel;
	}


	public PersonnelModel() {

	}

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


	public String getNom_departement() {
		return nom_departement;
	}

	public void setNom_departement(String nom_departement) {
		this.nom_departement = nom_departement;
	}

	public Long getId_departement() {
		return id_departement;
	}

	public void setId_departement(Long id_departement) {
		this.id_departement = id_departement;
	}

	public String getNom_filiere() {
		return nom_filiere;
	}

	public void setNom_filiere(String nom_filiere) {
		this.nom_filiere = nom_filiere;
	}

	public Long getId_filiere() {
		return id_filiere;
	}

	public void setId_filiere(Long id_filiere) {
		this.id_filiere = id_filiere;
	}

	public int getTypePersonnel() {
		return typePersonnel;
	}

	public void setTypePersonnel(int typePersonnel) {
		this.typePersonnel = typePersonnel;
	}


	@Override
	public String toString() {
		return "PersonnelModel{" +
				"idPersonnel=" + idPersonnel +
				", nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				", nom_departement='" + nom_departement + '\'' +
				", id_departement=" + id_departement +
				", nom_filiere='" + nom_filiere + '\'' +
				", id_filiere=" + id_filiere +
				", typePersonnel=" + typePersonnel +
				'}';
	}
}