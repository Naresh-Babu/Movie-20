package com.example.nareshintern.movielist;

import android.support.v7.widget.RecyclerView;

public class Movie {

    private String name,image;
    private float rating;

    public Movie(String name, String image, float rating) {
        this.name = name;
        this.image = image;
        this.rating = rating;
    }

    public Movie() {
        this.name = "KIll kon vodfsnvo sfnoi v o fmoibrobnoi vihlerafveario ferihlveiru efliqhciluchi";
        this.image = "http://placehold.it/120x120&text=image2";
        this.rating = 9.0f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
