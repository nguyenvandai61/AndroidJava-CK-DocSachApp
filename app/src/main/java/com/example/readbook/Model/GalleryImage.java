package com.example.readbook.Model;

import android.graphics.Bitmap;

public class GalleryImage {
    private int mId;
    private final int mWidth;
    private final int mHeight;

    private Bitmap image;

    public GalleryImage(int mWidth, int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmWidth() {
        return mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
