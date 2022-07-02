package com.example.authentification_cloud;

public class ParkingResponse{
    private int  id_park;
    private String photo ;
    private String nom ;
    private String commun ;
    private String tarif ;
    private Double longitude ;
    private Double latitude ;
    private String etat;
    private String Taux;
    private double distance ;
    private double duree;

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setTaux(String taux) {
        Taux = taux;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public String getEtat() {
        return etat;
    }

    public String getTaux() {
        return Taux;
    }

    public double getDistance() {
        return distance;
    }

    public double getDuree() {
        return duree;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }



    public void setId_park(int id_park) {
        this.id_park = id_park;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCommun(String commun) {
        this.commun = commun;
    }



    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public int getId_park() {
        return id_park;
    }

    public String getNom() {
        return nom;
    }

    public String getCommun() {
        return commun;
    }



    public String getTarif() {
        return tarif;
    }
}

