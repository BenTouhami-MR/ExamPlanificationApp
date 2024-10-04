package com.exam.core.web.models;

import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Niveau;
import com.exam.core.bo.TypeElement;

import java.util.ArrayList;
import java.util.List;

public class ElementPedagInitialData {

    List<TypeElement> typeElementList = new ArrayList<>();
    List<Enseignant> EnseignantsList = new ArrayList<>();
    List<Niveau> niveauList = new ArrayList<>();

    public List<TypeElement> getTypeElementList() {
        return typeElementList;
    }

    public void setTypeElementList(List<TypeElement> typeElementList) {
        this.typeElementList = typeElementList;
    }

    public List<com.exam.core.bo.Enseignant> getEnseignantsList() {
        return EnseignantsList;
    }

    public void setEnseignantsList(List<com.exam.core.bo.Enseignant> enseignantsList) {
        EnseignantsList = enseignantsList;
    }

    public List<Niveau> getNiveauList() {
        return niveauList;
    }

    public void setNiveauList(List<Niveau> niveauList) {
        this.niveauList = niveauList;
    }
}
