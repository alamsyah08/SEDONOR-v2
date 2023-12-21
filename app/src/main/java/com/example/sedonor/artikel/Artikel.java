package com.example.sedonor.artikel;

import android.widget.ImageView;

import java.util.List;

public class Artikel {
    String judul, konten, imageUrl;
    List<String> kategori;
    public Artikel() {
        // Diperlukan untuk deserialisasi Firestore
    }

    public Artikel(String judul, String konten, String imageUrl, List<String> kategori) {
        this.judul = judul;
        this.konten = konten;
        this.imageUrl = imageUrl;
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public List<String> getKategori() {
        return kategori;
    }

    public void setKategori(List<String> kategori) {
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "judul='" + judul + '\'' +
                ", konten='" + konten + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", kategori=" + kategori +
                '}';
    }

}
