package com.exam.core.web.models;

import com.exam.core.bo.ElementPedagogique;
import com.exam.core.bo.Enseignant;

public class ElemPedagDToProvider {

    private Long idElmPedg;
    private String titre;
    private String type;
    private String nom_cordinateur;
    private String dep_cordinateur;
    private String nom_enseignant;
    private String dep_enseignant;
    private String niveau;

    // New fields
    private Long idTypeElm;        // Type ID (Module/Element)
    private Long idCordinateur;    // Coordinator ID
    private Long idEnseignant;     // Teacher ID
    private Long idNiveau;         // Level ID

    // Constructor to initialize from ElementPedagogique entity
    public ElemPedagDToProvider(ElementPedagogique ep) {
        this.idElmPedg = ep.getIdElmtPedag();
        this.titre = ep.getTitre();
        this.type = ep.getTypeElement().getType();

        Enseignant cordinateur = ep.getCoordinateur();
        Enseignant enseignant = ep.getEnseignant();

        this.nom_cordinateur = cordinateur.getNom() + " " + cordinateur.getPrenom();
        this.dep_cordinateur = cordinateur.getDepartement().getNomDepartement();

        this.nom_enseignant = enseignant.getNom() + " " + enseignant.getPrenom();
        this.dep_enseignant = enseignant.getDepartement().getNomDepartement();

        this.niveau = ep.getNiveau().getType();

        // New attributes
        this.idTypeElm = ep.getTypeElement().getIdTypeElm();   // Getting Type ID
        this.idCordinateur = cordinateur.getIdPersonnel();     // Coordinator ID
        this.idEnseignant = enseignant.getIdPersonnel();       // Teacher ID
        this.idNiveau = ep.getNiveau().getIdNiv();             // Level ID
    }

    // Getters and setters for the new fields

    public Long getIdTypeElm() {
        return idTypeElm;
    }

    public void setIdTypeElm(Long idTypeElm) {
        this.idTypeElm = idTypeElm;
    }

    public Long getIdCordinateur() {
        return idCordinateur;
    }

    public void setIdCordinateur(Long idCordinateur) {
        this.idCordinateur = idCordinateur;
    }

    public Long getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Long idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Long getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Long idNiveau) {
        this.idNiveau = idNiveau;
    }

    // Existing getters and setters for other fields
    public Long getIdElmPedg() {
        return idElmPedg;
    }

    public void setIdElmPedg(Long idElmPedg) {
        this.idElmPedg = idElmPedg;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom_cordinateur() {
        return nom_cordinateur;
    }

    public void setNom_cordinateur(String nom_cordinateur) {
        this.nom_cordinateur = nom_cordinateur;
    }

    public String getDep_cordinateur() {
        return dep_cordinateur;
    }

    public void setDep_cordinateur(String dep_cordinateur) {
        this.dep_cordinateur = dep_cordinateur;
    }

    public String getNom_enseignant() {
        return nom_enseignant;
    }

    public void setNom_enseignant(String nom_enseignant) {
        this.nom_enseignant = nom_enseignant;
    }

    public String getDep_enseignant() {
        return dep_enseignant;
    }

    public void setDep_enseignant(String dep_enseignant) {
        this.dep_enseignant = dep_enseignant;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "ElemPedagDToProvider{" +
                "idElmPedg=" + idElmPedg +
                ", titre='" + titre + '\'' +
                ", type='" + type + '\'' +
                ", nom_cordinateur='" + nom_cordinateur + '\'' +
                ", dep_cordinateur='" + dep_cordinateur + '\'' +
                ", nom_enseignant='" + nom_enseignant + '\'' +
                ", dep_enseignant='" + dep_enseignant + '\'' +
                ", niveau='" + niveau + '\'' +
                ", idTypeElm=" + idTypeElm +
                ", idCordinateur=" + idCordinateur +
                ", idEnseignant=" + idEnseignant +
                ", idNiveau=" + idNiveau +
                '}';
    }
}
