package com.example.nareshintern.movielist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity {

    String name;
    String email;
    String picture;

    ImageView pro_pic;
    EditText pro_name;
    EditText pro_email;
    ImageView pro_logo;

    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //storage
        mSettings = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = mSettings.edit();



        setContentView(R.layout.activity_profile);

        name = mSettings.getString("name",null);
        email = mSettings.getString("email",null);
        picture = mSettings.getString("picture",null);

        pro_logo = findViewById(R.id.pro_logo);
        pro_pic = findViewById(R.id.pro_pic);
        pro_name = findViewById(R.id.pro_name);
        pro_email = findViewById(R.id.pro_email);
        Picasso.get().load(R.drawable.movie_logo).into (pro_logo);
        ImageView pro_bg = (ImageView)findViewById(R.id.pro_background);
        Picasso.get().load(R.drawable.movie_background).resize(400,800).centerCrop().into(pro_bg);
        if(picture!=null)
            Picasso.get().load(picture).into(pro_pic);
        else
            Picasso.get().load(R.drawable.place_holder).into(pro_pic);
        if(name!=null)
            pro_name.setText(name);
        if(email!=null)
            pro_email.setText(email);

        Button launch = findViewById(R.id.entry);
        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
                finish();

            }
        });
        TextView cl = findViewById(R.id.clear);
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                Toast.makeText(getApplicationContext(), "Cleared, restart to sign in.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.menu_save:
                editor.putString("name",pro_name.getText().toString());
                editor.putString("email",pro_email.getText().toString());
                editor.apply();
                Toast.makeText(getBaseContext(), "Changes saved Successfully.", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

}
