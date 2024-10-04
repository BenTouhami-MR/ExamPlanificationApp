package com.exam.core.web.models;

import com.exam.core.bo.Departement;
import com.exam.core.bo.Filiere;
import com.exam.core.bo.Groupe;

import java.util.ArrayList;
import java.util.List;

public class GroupInitialData {


    private List<FiliereModel> filieresList =new ArrayList<>();

    private List <DepartementModel> departementsList = new ArrayList<>();

    public List<FiliereModel> getFilieresList() {
        return filieresList;
    }

    public GroupInitialData(List<Filiere> fs, List<Departement> ds){
        for (Filiere f :fs){
            filieresList.add(new FiliereModel(f));
        }
         for (Departement d :ds){
             departementsList.add(new DepartementModel(d));
        }

    }
    public void setFilieresList(List<FiliereModel> filieresList) {
        this.filieresList = filieresList;
    }

    public List<DepartementModel> getDepartementsList() {
        return departementsList;
    }

    public void setDepartementsList(List<DepartementModel> departementsList) {
        this.departementsList = departementsList;
    }
}
