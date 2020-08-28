package com.github.panarik.smartlauncher;

import android.graphics.drawable.Drawable;

public class AppObject {

    private String name;
    private String packageName;
    private Drawable image;


    public AppObject(String name, String packageName, Drawable image) {
        this.name = name;
        this.packageName = packageName;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getImage() {
        return image;
    }
}
