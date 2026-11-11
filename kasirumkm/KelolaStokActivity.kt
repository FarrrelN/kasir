package com.example.kasirumkm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class KelolaStokActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_stok)

        val etId = findViewById<EditText>(R.id.etIdProduk)
        val etNama = findViewById<EditText>(R.id.etNamaProduk)
        val etHarga = findViewById<EditText>(R.id.etHargaProduk)
        val etStok = findViewById<EditText>(R.id.etStokProduk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpanStok)

        btnSimpan.setOnClickListener {
            val id = etId.text.toString().trim()
            val nama = etNama.text.toString().trim()
            val harga = etHarga.text.toString().toDoubleOrNull() ?: 0.0
            val stok = etStok.text.toString().toIntOrNull() ?: 0

            if (id.isEmpty() || nama.isEmpty() || stok <= 0) {
                Toast.makeText(this, "Isi data dengan benar!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val existing = DataProduk.listProduk.find { it.id.equals(id, ignoreCase = true) }
            if (existing != null) {
                DataProduk.tambahStok(existing.id, stok)
                Toast.makeText(this, "Stok ${existing.nama} berhasil ditambah $stok", Toast.LENGTH_SHORT).show()
            } else {
                val produkBaru = Produk(id, nama, harga, stok, DataProduk.defaultIcon)
                DataProduk.listProduk.add(produkBaru)
                Toast.makeText(this, "Produk $nama berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
            }

            finish()
        }
    }
}