package com.example.sedonor.lokasi

import com.google.firebase.firestore.GeoPoint
data class LokasiDonor(
    val deskripsi: String? = null,
    val foto: String? = null,
    val koordinat: GeoPoint? = null,
    val lokasi: String? = null,
    val nama: String? = null
)
