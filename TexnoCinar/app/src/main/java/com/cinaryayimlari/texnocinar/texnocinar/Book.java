package com.cinaryayimlari.texnocinar.texnocinar;

/**
 * Created by Aws on 28/01/2018.
 */

public class Book {

    private String Title;
    private int Category ;
    private String Description ;
    private int Thumbnail;
    public static int Header = 0;
    public static int Card =1 ;
    public static int Test =2 ;



    public Book() {
    }


    public Book(String title, int category, String description, int thumbnail) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;

    }


    public String getTitle() {
        return Title;
    }

    public int getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
