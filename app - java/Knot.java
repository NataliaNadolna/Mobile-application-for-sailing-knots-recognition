package com.example.wzy;

import android.widget.VideoView;

import java.io.Serializable;

public class Knot implements Serializable {
    private String nazwa;
    private String zdjecie;
    private String kategoria;
    private String opis;
    private String film;

    public Knot(String nazwa, String zdjecie, String kategoria, String opis, String film){
        this.nazwa = nazwa;
        this.zdjecie = zdjecie;
        this.kategoria = kategoria;
        this.opis = opis;
        this.film = film;
    }

    public String getNazwa(){
        return nazwa;
    }
    public String getZdjecie(){
        return zdjecie;
    }
    public String getKategoria(){
        return kategoria;
    }
    public String getOpis(){
        return opis;
    }
    public String getFilm(){
        return film;
    }
}
