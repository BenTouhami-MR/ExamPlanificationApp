 package com.exam.core.web.models;

 import com.exam.core.bo.Session;
 import org.springframework.beans.BeanUtils;

 public class SessionModel {


    private Long idSession;


    private String intitule;



    public SessionModel (Session s){
        BeanUtils.copyProperties(s,this);
    }




    public Long getIdSession() {
        return idSession;
    }


    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }


    public String getIntitule() {
        return intitule;
    }


    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }


}
