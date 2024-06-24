package com.example.wzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class KnotActivity extends AppCompatActivity {

    TextView nazwa, opis;
    String sciezka;
    ImageView zdjecie;
    VideoView film;
    Knot object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knot);

        initView();
        getBundle();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.homeBtn);
        LinearLayout listaBtn = findViewById(R.id.listaBtn);
        LinearLayout galeriaBtn = findViewById(R.id.galeriaBtn);
        LinearLayout aparatBtn = findViewById(R.id.aparatBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KnotActivity.this, ListActivity.class));
            }
        });
        listaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KnotActivity.this, ListActivity.class));
            }
        });
        galeriaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KnotActivity.this, MainActivity.class));
            }
        });
        aparatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KnotActivity.this, GalleryActivity.class));
            }
        });
    }

    private void getBundle() {
        object = (Knot) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getZdjecie(), "drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(zdjecie);
        nazwa.setText(object.getNazwa());
        opis.setText(object.getOpis());

        sciezka = object.getFilm();
        //sciezka = "android.resource://" + getPackageName() + "/" + R.raw.zwykly_film;
        Uri uri = Uri.parse(sciezka);
        film.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        film.setMediaController(mediaController);
        mediaController.setAnchorView(film);
    }

    private void initView() {
        nazwa = findViewById(R.id.profile_name);
        opis = findViewById(R.id.profile_discription);
        zdjecie = findViewById(R.id.profile_image);
        film = findViewById(R.id.profile_video);
    }
}