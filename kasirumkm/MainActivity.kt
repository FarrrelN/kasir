package com.example.kasirumkm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)

        // Set item aktif awal
        bottomNavigation.selectedItemId = R.id.nav_home

        // Handling Listener Navigasi
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Berada di halaman utama / kasir
                    true
                }
                R.id.nav_riwayat -> {
                    val intent = Intent(this, RiwayatActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_laporan -> {
                    Toast.makeText(this, "Fitur Laporan segera hadir", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}