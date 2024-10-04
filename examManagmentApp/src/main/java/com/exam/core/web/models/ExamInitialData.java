package com.exam.core.web.models;

import com.exam.core.bo.*;

import java.util.ArrayList;
import java.util.List;

public class ExamInitialData {

private List<SessionModel> listSessions =new ArrayList<>();

    private List<SemestreModel> listSmestres =new ArrayList<>();

    private List<SalleModel> listSalles = new ArrayList<>();

    private List<ElementPedagogiqueModel> listElmPedags = new ArrayList<>();

    private List<TypeExamModel> listTypeExams =new ArrayList<>();

    private List<EnseignantModel> listenseignants =new ArrayList<>();


    public ExamInitialData(List<Salle> salles,List<ElementPedagogique> eps, List<Enseignant> engs,List<Session>ss,List<Semestre>sms,List<TypeExam>tes){
        for (Enseignant e : engs){
            this.listenseignants.add(new EnseignantModel(e));
        }
        for (Salle s : salles){
            this.listSalles.add(new SalleModel(s));
        }
        for (ElementPedagogique ep: eps){
            this.listElmPedags.add(new ElementPedagogiqueModel(ep));
        }
        for (Session s: ss){
            this.listSessions.add(new SessionModel(s));
        }
        for (Semestre s: sms){
            this.listSmestres.add(new SemestreModel(s));
        }
        for (TypeExam te: tes){
            this.listTypeExams.add(new TypeExamModel(te));
        }
    }


    public List<SessionModel> getListSessions() {
        return listSessions;
    }

    public void setListSessions(List<SessionModel> listSessions) {
        this.listSessions = listSessions;
    }

    public List<SemestreModel> getListSmestres() {
        return listSmestres;
    }

    public void setListSmestres(List<SemestreModel> listSmestres) {
        this.listSmestres = listSmestres;
    }

    public List<SalleModel> getListSalles() {
        return listSalles;
    }

    public void setListSalles(List<SalleModel> listSalles) {
        this.listSalles = listSalles;
    }

    public List<ElementPedagogiqueModel> getListElmPedags() {
        return listElmPedags;
    }

    public void setListElmPedags(List<ElementPedagogiqueModel> listElmPedags) {
        this.listElmPedags = listElmPedags;
    }

    public List<TypeExamModel> getListTypeExams() {
        return listTypeExams;
    }

    public void setListTypeExams(List<TypeExamModel> listTypeExams) {
        this.listTypeExams = listTypeExams;
    }

    public List<EnseignantModel> getListenseignants() {
        return listenseignants;
    }

    public void setListenseignants(List<EnseignantModel> listenseignants) {
        this.listenseignants = listenseignants;
    }
}
