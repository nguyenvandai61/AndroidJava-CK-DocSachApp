package com.example.readbook.Model;

public class Book {
    private String mName;
    private String mAuthor;
    private GalleryImage mImage;

    public Book(String mName, GalleryImage mImage) {
        this.mName = mName;
        this.mImage = mImage;
    }

    public Book(String mName, String mAuthor, GalleryImage mImage) {
        this.mName = mName;
        this.mAuthor = mAuthor;
        this.mImage = mImage;
    }
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public GalleryImage getmImage() {
        return mImage;
    }

    public void setmImage(GalleryImage mImage) {
        this.mImage = mImage;
    }
}
