package com.example.sedonor.lokasi

import com.google.firebase.firestore.GeoPoint
data class LokasiDonor(
    val deskripsi: String,
    val foto: String,
    val koordinat: GeoPoint,
    val lokasi: String,
    val nama: String
)
