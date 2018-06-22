package com.example.nareshintern.movielist;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class Profile {

    String Name;
    Uri picture;
    String email;

    public Profile() throws MalformedURLException {
        Name="Anonymous";
        picture= null;
        email="bestmovies@movie20.com";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Uri getPicture() {
        return picture;
    }

    public void setPicture(Uri picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
