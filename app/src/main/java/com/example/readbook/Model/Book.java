package com.example.readbook.Model;

import android.graphics.Bitmap;

public class Book {
    private long mId;
    private String mName;
    private String mAuthor;
    private String mPath;
    private Bitmap mImage;
    public Book(long mId, String mName, String mAuthor) {
        this.mId = mId;
        this.mName = mName;
        this.mImage = mImage;
    }

    public Book(long mId, String mName, Bitmap mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mImage = mImage;
    }

    public Book(String mName, String mAuthor, Bitmap mImage) {
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

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public String getmPath() {
        return mPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mPath='" + mPath + '\'' +
                ", mImage=" + mImage +
                '}';
    }
}
