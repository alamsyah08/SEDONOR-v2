package com.example.sedonor.lokasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sedonor.MapsActivity
import com.example.sedonor.R

class DetailLokasiActivity : AppCompatActivity() {
    private lateinit var tvNama: TextView
    private lateinit var tvLokasi: TextView
    private lateinit var tvDeskripsi: TextView
    private lateinit var tvImg: ImageView

    private lateinit var vnama: String
    private lateinit var vlatitude : String
    private lateinit var vlongitude : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_lokasi)

        tvImg = findViewById<ImageView>(R.id.imgView)
        tvNama = findViewById<TextView>(R.id.tvNama)
        tvLokasi = findViewById<TextView>(R.id.tvLokasi)
        tvDeskripsi = findViewById<TextView>(R.id.tvKontenDeskripsi)

        // Get data from intent
        val nama = intent.getStringExtra("nama")
        val lokasi = intent.getStringExtra("lokasi")
        val image = intent.getStringExtra("foto")
        val deskripsi = intent.getStringExtra("deskripsi")
        val latitude = intent.getStringExtra("klatitude")
        val longitude = intent.getStringExtra("klongitude")

        vnama = nama.toString()
        vlatitude = latitude.toString()
        vlongitude = longitude.toString()

        tvNama.text = nama
        tvLokasi.text = lokasi
        tvDeskripsi.text = deskripsi
        Glide.with(this)
            .load(image)
            .placeholder(R.drawable.ic_back) //cari gambar loading
            .into(tvImg)
    }

    fun intentBack (view: View) {
        // Ini akan dipanggil ketika tombol "Lihat semua" ditekan
        val intent = Intent(this, LokasiDonorActivity::class.java)
        startActivity(intent)
    }

    fun intentKeMaps(view: View) {
        // Ini akan dipanggil ketika tombol "Lihat semua" ditekan
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("nama", vnama)
        intent.putExtra("klatitude", vlatitude)
        intent.putExtra("klongitude", vlongitude)
        startActivity(intent)
    }
}