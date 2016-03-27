package com.odinn.anotherversion.models;

public class Sights {
    private int id;
    private String title;
    private int img;



    private double lat;
    private double lng;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Sights(int id, String title, int img, double lat, double lng) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.lat = lat;
        this.lng = lng;
    }

    public Sights(int id, String title, int img ) {
        this.id = id;
        this.title = title;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
