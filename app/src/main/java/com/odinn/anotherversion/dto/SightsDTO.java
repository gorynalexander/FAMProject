package com.odinn.anotherversion.dto;




public class SightsDTO {
    private int id;
    private String title;
    private int img;


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

    public SightsDTO(int id, String title, int img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
