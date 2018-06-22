package com.example.nareshintern.movielist;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> { //implements LoaderManager.LoaderCallbacks<Bitmap>{

    private Context context;
    private List<Movie> data;
    public static String url;
    private static final int LOADER_ID = 1;
    private static final String ACCESS = "https://image.tmdb.org/t/p/w200";


    public CustomAdapter(Context context, List<Movie> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_view, parent,false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {
        holder.load.setVisibility(View.VISIBLE);
        holder.name.setSelected(true);
        holder.name.setText(data.get(position).getName());
        url = data.get(position).getImage();
        holder.rating.setText("" + data.get(position).getRating());
        Log.i("PATHS", data.get(position).getName()+ACCESS+data.get(position).getImage()+position);
        Picasso.get().load(ACCESS+data.get(position).getImage()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.load.setVisibility(View.INVISIBLE);
                holder.image.setVisibility(View.VISIBLE);

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

        //new MovieImageLoader(context,data.get(position).getImage());

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

//
//    @Override
//    public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
//        Log.i("ERRORRR", "hi");
//        return new MovieImageLoader(context,url);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Bitmap> loader, Bitmap data) {
//        image.setImageBitmap(data);
//        image.setVisibility(View.VISIBLE);
//        ViewHolder.load.setVisibility(View.INVISIBLE);
//    }
//
//
//    @Override
//    public void onLoaderReset(Loader loader) {
//
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView rating, name;
         ImageView image;
         FrameLayout load;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.movie_name);
            rating = (TextView) itemView.findViewById(R.id.rating);
            image = (ImageView) itemView.findViewById(R.id.movie_image);
            load = itemView.findViewById(R.id.movie_image1);


        }
    }



}