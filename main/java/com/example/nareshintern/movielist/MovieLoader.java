package com.example.nareshintern.movielist;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;


import java.util.List;

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    private static final String LOG_TAG = MovieLoader.class.getName();
    List<Movie> movies;
    private static String url;

    public MovieLoader(Context context,String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        if (url == null || url.isEmpty()) {
            return null;
        }

         movies = Utils.fetchData(url);
        return movies;

    }
}
