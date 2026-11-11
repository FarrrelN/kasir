package com.example.kasirumkm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kasirumkm.R // Import R project[cite: 2]
import com.example.kasirumkm.databinding.ActivityProdukBinding // Mengacu pada activity_produk.xml

class ProdukActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProdukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan ViewBinding dari activity_produk.xml
        binding = ActivityProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}