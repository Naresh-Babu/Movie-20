package com.example.nareshintern.movielist;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {

    private ArrayList<Movie> movieArrayList;

    private static final int LOADER_ID = 1;

    private static final String MovieURL="https://api.themoviedb.org/3/discover/movie?api_key=4c9a3a8bafc1422cef07b7ae4beb58dd&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false";

    private CustomAdapter ca;

    private FrameLayout empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieArrayList = new ArrayList<>();



        RecyclerView rv = findViewById(R.id.recycler_view);
        empty = findViewById(R.id.load_frame);
        empty.setVisibility(View.VISIBLE);
        ca = new CustomAdapter(this,movieArrayList);
        int orientation = this.getResources().getConfiguration().orientation;
        int span;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            span=2;
        } else {
            span=3;
        }
        rv.setLayoutManager(new GridLayoutManager(this,span));

        rv.setAdapter(ca);


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID, null, this);
            Log.d("BEFR","VLGJVL");

        } else {
            Log.i("Main", "onCreate: sssss");
            empty.setVisibility(View.GONE);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.Menu:

                Intent i = new Intent(this.getBaseContext(),ProfileActivity.class);
                startActivity(i);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {


        return new MovieLoader(this,MovieURL);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        empty.setVisibility(View.GONE);

        if(data!=null && !data.isEmpty())
            movieArrayList.addAll(data);
        ca.notifyDataSetChanged();
        getLoaderManager().destroyLoader(LOADER_ID);
    }



    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        int size = movieArrayList.size();
        movieArrayList.clear();
        ca.notifyItemRangeRemoved(0,size);
    }
}
