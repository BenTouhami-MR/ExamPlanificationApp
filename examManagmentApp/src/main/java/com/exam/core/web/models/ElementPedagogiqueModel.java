package com.exam.core.web.models;

import com.exam.core.bo.*;

import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;

public class ElementPedagogiqueModel {

    private Long IdElmtPedag;

    private String titre;


    private Enseignant enseignant;


    private Enseignant coordinateur = enseignant;

    private Niveau niveau;

    private TypeElement typeElement;

    public ElementPedagogiqueModel(ElementPedagogique ep){
        BeanUtils.copyProperties(ep,this);
    }






    public Long getIdElmtPedag() {
        return IdElmtPedag;
    }


    public void setIdElmtPedag(Long idElmtPedag) {
        IdElmtPedag = idElmtPedag;
    }


    public String getTitre() {
        return titre;
    }


    public void setTitre(String titre) {
        this.titre = titre;
    }


    public Enseignant getEnseignant() {
        return enseignant;
    }


    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;

    }


    public Enseignant getCoordinateur() {
        return coordinateur;
    }


    public void setCoordinateur(Enseignant coordinateur) {
        this.coordinateur = coordinateur;
    }


    public Niveau getNiveau() {
        return niveau;
    }


    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }


    public TypeElement getTypeElement() {
        return typeElement;
    }


    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }






}
