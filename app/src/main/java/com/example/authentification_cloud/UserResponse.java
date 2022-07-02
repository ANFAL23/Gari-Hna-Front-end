package com.example.authentification_cloud;

public class UserResponse {
    private int  id_user;
    private String nom ;
    private String prenom ;
    private String email ;
    private String num_tel ;

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNum_tel() {
        return num_tel;
    }


}
