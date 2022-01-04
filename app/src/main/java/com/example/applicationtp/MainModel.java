package com.example.applicationtp;

public class MainModel {

    String  etat,img,localisation,marque,matricule,panne;

    MainModel()
    {

    }


    public MainModel(String etat, String img, String localisation, String marque, String matricule, String panne) {
        this.etat = etat;
        this.img = img;
        this.localisation = localisation;
        this.marque = marque;
        this.matricule = matricule;
        this.panne = panne;
    }



    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtats(String etat) {
        this.etat = etat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPanne() {
        return panne;
    }

    public void setPanne(String panne) {
        this.panne = panne;
    }
}
