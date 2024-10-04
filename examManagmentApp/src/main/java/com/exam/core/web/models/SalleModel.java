package com.exam.core.web.models;

import com.exam.core.bo.Salle;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

public class SalleModel {
    private Long idSalle;

    private String nomSalle;

    private int nbreSurveillants;

    private int capacite;


    public SalleModel(Salle s){
        BeanUtils.copyProperties(s,this);
    }

    public Long getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Long idSalle) {
        this.idSalle = idSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNbreSurveillants() {
        return nbreSurveillants;
    }

    public void setNbreSurveillants(int nbreSurveillants) {
        this.nbreSurveillants = nbreSurveillants;
    }
}
