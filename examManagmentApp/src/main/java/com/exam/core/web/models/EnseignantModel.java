package com.exam.core.web.models;

import com.exam.core.bo.Departement;
import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Filiere;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.io.FileReader;
import java.util.List;

public class EnseignantModel {


    private Long idPersonnel;

    private String nom;


    private String prenom;

    private FiliereModel filiere;

    private DepartementModel departement;

    private boolean possedeUnGroupe;


    public EnseignantModel(Enseignant e){
        this.setNom(e.getNom());
        this.setPrenom(e.getPrenom());
        this.setIdPersonnel(e.getIdPersonnel());

        FiliereModel fm = new FiliereModel();
        Filiere f = e.getFiliere();
        BeanUtils.copyProperties(f,fm);
        this.setFiliere(fm);

        DepartementModel dm =new DepartementModel();
        Departement d =e.getDepartement();
        BeanUtils.copyProperties(d,dm);
        this.setDepartement(dm);


        if(e.getGroupe()==null){
            this.setPossedeUnGroupe(false);

        }else {
            this.setPossedeUnGroupe(true);
        }


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

    public FiliereModel getFiliere() {
        return filiere;
    }

    public void setFiliere(FiliereModel filiere) {
        this.filiere = filiere;
    }

    public DepartementModel getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementModel departement) {
        this.departement = departement;
    }

    public boolean isPossedeUnGroupe() {
        return possedeUnGroupe;
    }

    public void setPossedeUnGroupe(boolean possedeUnGroupe) {
        this.possedeUnGroupe = possedeUnGroupe;
    }

}
