package com.cinaryayimlari.texnocinar.texnocinar;

/**
 * Created by User on 28.03.2018.
 */

public class VideoItem {
    private String video;
    private String test;
    private String  sual;
    private String book;
    private String id;

    public VideoItem() {
    }

    public VideoItem(String video, String test, String sual, String book, String id) {
        this.video = video;
        this.test = test;
        this.sual = sual;
        this.book = book;
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getSual() {
        return sual;
    }

    public void setSual(String sual) {
        this.sual = sual;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
