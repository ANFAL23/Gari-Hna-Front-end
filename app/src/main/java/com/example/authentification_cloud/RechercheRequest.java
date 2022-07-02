package com.example.authentification_cloud;

public class RechercheRequest {
    private String latitude ;
    private String longitude ;
    private Double distance_max  ;
    private Double Prix_max;

    public Double getDistance_max() {
        return distance_max;
    }

    public void setDistance_max(Double distance_max) {
        this.distance_max = distance_max;
    }

    public void setPrix_max(Double prix_max) {
        Prix_max = prix_max;
    }

    public Double getPrix_max() {
        return Prix_max;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;

    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
