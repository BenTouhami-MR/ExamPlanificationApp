package com.exam.core.web.models;

import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Filiere;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class FiliereModel {
    private Long idFil;

    private String nomFiliere;

    private List<EnseignantModel> enseignants =new ArrayList<>();

    public FiliereModel(){

    }
    public FiliereModel(Filiere f){
        BeanUtils.copyProperties(f,this);
        for(Enseignant ef : f.getEnseignants()){

            enseignants.add(new EnseignantModel(ef));
        }
    }

    public Long getIdFil() {
        return idFil;
    }

    public void setIdFil(Long idFil) {
        this.idFil = idFil;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public List<EnseignantModel> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<EnseignantModel> enseignants) {
        this.enseignants = enseignants;
    }

    public void setNomFiliere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }
}
