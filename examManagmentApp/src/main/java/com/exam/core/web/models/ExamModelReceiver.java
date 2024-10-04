package com.exam.core.web.models;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ExamModelReceiver {
    public static final Long TYPE_MODULE=1L;
    public static final Long TYPE_ELEMENT=2L;


    private Long IdExam;





    @NotNull
    private Long typeExam;
//LocalDate
    private LocalDate date;
    //LocalTime
    private LocalTime heureDebut;

    private int dureePrevue;

    private String session;

    private String semestre;


    private int durreeReelle;

    private String anneeUniversitaire;

    private String epreuve;

    private String PV;


    private String rapportTextuelle = "Rien à signaler";

    private Long elementPedagogique;





    private List<Long> salles =new ArrayList<>();
    private List<Integer>  nbreSurveillants =new ArrayList<>();

    private Long cordonnateur;

    public Long getIdExam() {
        return IdExam;
    }

    public void setIdExam(Long idExam) {
        IdExam = idExam;
    }

    public @NotNull Long getTypeExam() {
        return typeExam;
    }

    public void setTypeExam(@NotNull Long typeExam) {
        this.typeExam = typeExam;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getDureePrevue() {
        return dureePrevue;
    }

    public void setDureePrevue(int dureePrevue) {
        this.dureePrevue = dureePrevue;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getDurreeReelle() {
        return durreeReelle;
    }

    public void setDurreeReelle(int durreeReelle) {
        this.durreeReelle = durreeReelle;
    }

    public String getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public String getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(String epreuve) {
        this.epreuve = epreuve;
    }

    public String getPV() {
        return PV;
    }

    public void setPV(String PV) {
        this.PV = PV;
    }

    public String getRapportTextuelle() {
        return rapportTextuelle;
    }

    public void setRapportTextuelle(String rapportTextuelle) {
        this.rapportTextuelle = rapportTextuelle;
    }

    public Long getElementPedagogique() {
        return elementPedagogique;
    }

    public void setElementPedagogique(Long elementPedagogique) {
        this.elementPedagogique = elementPedagogique;
    }

    public List<Long> getSalles() {
        return salles;
    }

    public void setSalles(List<Long> salles) {
        this.salles = salles;
    }

    public Long getCordonnateur() {
        return cordonnateur;
    }

    public void setCordonnateur(Long cordonnateur) {
        this.cordonnateur = cordonnateur;
    }

    public List<Integer> getNbreSurveillants() {
        return nbreSurveillants;
    }

    public void setNbreSurveillants(List<Integer> nbreSurveillants) {
        this.nbreSurveillants = nbreSurveillants;
    }
}
