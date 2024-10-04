package com.exam.core.web.models;


import com.exam.core.bo.TypeExam;
import org.springframework.beans.BeanUtils;

public class TypeExamModel {




    private Long idTypeExam;


    private String intitule;

    public TypeExamModel(TypeExam te){
        BeanUtils.copyProperties(te,this);
    }


    public Long getIdTypeExam() {
        return idTypeExam;
    }


    public void setIdTypeExam(Long idTypeExam) {
        this.idTypeExam = idTypeExam;
    }


    public String getIntitule() {
        return intitule;
    }


    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }




}
