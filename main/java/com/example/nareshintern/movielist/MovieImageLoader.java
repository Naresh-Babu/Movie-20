package com.example.nareshintern.movielist;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class MovieImageLoader extends AsyncTaskLoader<Bitmap> {

    private String url;

    private static final String ACCESS = "https://image.tmdb.org/t/p/w200";

    public MovieImageLoader(Context context, String image) {
        super(context);
        url = ACCESS + image;
        forceLoad();
    }

    @Override
    public Bitmap loadInBackground() {

        Bitmap icon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            icon = BitmapFactory.decodeStream(in);
            in.close();
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
            e.printStackTrace();
        }

        return icon;
    }
}
