package com.student.nicolgom.secretsantahelper;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Nicolas on 11/19/2017.
 */

public class Contact implements Serializable{
    String name, number;
    Bitmap photo;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", photo=" + photo +
                '}';
    }

    public Contact(String name, String number, Bitmap photo) {
        this.name = name;
        this.number = number;
        this.photo = photo;
    }
}
