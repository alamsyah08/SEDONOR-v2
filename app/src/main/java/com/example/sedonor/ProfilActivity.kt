package com.example.sedonor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.sedonor.scanner.CheckinBerhasil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProfilActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var tvNama: TextView
    private lateinit var tvNomor: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvTerakhirDonor: TextView
    private lateinit var tvDomisili: TextView

    lateinit var retrievedUserId : String
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val db = FirebaseFirestore.getInstance()
        sessionManager = SessionManager(this)
        retrievedUserId = sessionManager.getUserId().toString()

        tvNama = findViewById(R.id.tvNama)
        tvNomor = findViewById(R.id.tvNomor)
        tvEmail = findViewById(R.id.tvEmail)
        tvTotal = findViewById(R.id.tvDonor)
        tvTerakhirDonor = findViewById(R.id.tvTerakhirdonor)
        tvDomisili = findViewById(R.id.tvDomisili)

        db.collection("users").document(retrievedUserId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = convertDocumentSnapshotToUser(task.result)
                    tvNama.text = user!!.nama
                    tvNomor.text = user!!.kodeDonor
                    tvEmail.text = user!!.email
                    tvTerakhirDonor.text = user!!.checkin
                    tvDomisili.text = user!!.domisili
                }
            }

        val collectionReference = db.collection("users").document(retrievedUserId).collection("riwayat")
        collectionReference.whereEqualTo("status", true)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result.documents
                    val total = document.size.toString()
                    tvTotal.text = total
                }
            }


        bottomNavigationView = findViewById(R.id.bottom_navbar)
        setupBottomNavigationBar()
    }

    private fun convertDocumentSnapshotToUser(documentSnapshot: DocumentSnapshot): Users? {
        return documentSnapshot.toObject(Users::class.java)
    }

    private fun showToastAndNavigate(message: String, destination: Class<*>) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, destination))
    }

    private fun setupBottomNavigationBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            handleBottomNavigationItemClick(item)
        }
    }

    private fun handleBottomNavigationItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_1 -> {
                item.setIcon(R.drawable.ic_home_selected)
                showToastAndNavigate("Home Clicked", HomePage::class.java)
            }
            R.id.menu_item_2 -> {
                item.setIcon(R.drawable.ic_tanyapmi_selected)
                showToastAndNavigate("Tanya PMI Clicked", ChatbotPMI::class.java)
            }
            R.id.menu_item_3 -> {
                item.setIcon(R.drawable.ic_akun_selected)
                // Lakukan aksi atau pindah ke halaman Account
//                showToastAndNavigate("Account Clicked", Account::class.java)
            }
        }
        return true
    }

    fun intentBack (view: View) {
        // Ini akan dipanggil ketika tombol "Lihat semua" ditekan
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
}