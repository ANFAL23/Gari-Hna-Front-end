package com.example.authentification_cloud;

public class ReservationRequest
{


    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    private int  id_res;
    private int  id_park;
    private int id_user ;
    private String entree ;
    private String sortie ;
    private String date ;
    private Boolean valider  ;
    public int getId_res() {
        return id_res;
    }

    public void setId_park(int id_park) {
        this.id_park = id_park;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setEntree(String entree) {
        this.entree = entree;
    }

    public void setSortie(String sortie) {
        this.sortie = sortie;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValider(Boolean valider) {
        this.valider = valider;
    }

    public int getId_park() {
        return id_park;
    }

    public int getId_user() {
        return id_user;
    }

    public String getEntree() {
        return entree;
    }

    public String getSortie() {
        return sortie;
    }

    public String getDate() {
        return date;
    }

    public Boolean getValider() {
        return valider;
    }
}
