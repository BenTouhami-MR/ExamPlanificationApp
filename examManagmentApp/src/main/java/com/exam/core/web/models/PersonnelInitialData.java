package com.exam.core.web.models;

import com.exam.core.bo.Departement;
import com.exam.core.bo.Filiere;

import java.util.ArrayList;
import java.util.List;

public class PersonnelInitialData {


    private List<Filiere> filieresList =new ArrayList<>();

    private List <Departement> DepartementsList = new ArrayList<>();

    public List<Filiere> getFilieresList() {
        return filieresList;
    }

    public void setFilieresList(List<Filiere> filieresList) {
        this.filieresList = filieresList;
    }

    public List<Departement> getDepartementsList() {
        return DepartementsList;
    }

    public void setDepartementsList(List<Departement> departementsList) {
        DepartementsList = departementsList;
    }
}
