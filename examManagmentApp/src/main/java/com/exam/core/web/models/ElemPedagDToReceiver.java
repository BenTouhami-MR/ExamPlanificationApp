package com.exam.core.web.models;

public class ElemPedagDToReceiver {

        private Long idCordinateur;
        private Long idEnseignant;
        private Long idNiveau;
        private Long idTypeElm;
        private String titre;

        // Default constructor
        public ElemPedagDToReceiver() {
        }

        // Parameterized constructor
        public ElemPedagDToReceiver(Long idCordinateur, Long idEnseignant, Long idNiveau, Long idTypeElm, String titre) {
            this.idCordinateur = idCordinateur;
            this.idEnseignant = idEnseignant;
            this.idNiveau = idNiveau;
            this.idTypeElm = idTypeElm;
            this.titre = titre;
        }

        // Getters and Setters
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

        public Long getIdTypeElm() {
            return idTypeElm;
        }

        public void setIdTypeElm(Long idTypeElm) {
            this.idTypeElm = idTypeElm;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        @Override
        public String toString() {
            return "ElemPedagDToProvider{" +
                    "idCordinateur=" + idCordinateur +
                    ", idEnseignant=" + idEnseignant +
                    ", idNiveau=" + idNiveau +
                    ", idTypeElm=" + idTypeElm +
                    ", titre='" + titre + '\'' +
                    '}';
        }


}
