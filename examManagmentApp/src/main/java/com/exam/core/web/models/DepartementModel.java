package com.exam.core.web.models;


import com.exam.core.bo.Departement;
import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Filiere;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

import java.util.List;

public class DepartementModel {
    private Long idDep;

    private String nomDepartement;

    private List<EnseignantModel> enseignants = new ArrayList<>();
    public DepartementModel(){

    }
    public DepartementModel(Departement d){
        BeanUtils.copyProperties(d,this);

        for(Enseignant ef : d.getEnseignants()){

            enseignants.add(new EnseignantModel(ef));
        }
    }

    public Long getIdDep() {
        return idDep;
    }

    public void setIdDep(Long idDep) {
        this.idDep = idDep;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public List<EnseignantModel> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<EnseignantModel> enseignants) {
        this.enseignants = enseignants;
    }
}
