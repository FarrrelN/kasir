package com.example.kasirumkm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class KeranjangActivity : AppCompatActivity() {

    private lateinit var rvKeranjang: RecyclerView
    private lateinit var tvTotalPembayaran: TextView
    private lateinit var btnBayarSekarang: Button

    private var listKeranjang = mutableListOf<ItemKeranjang>()
    private lateinit var adapter: KeranjangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)

        // Inisialisasi View
        rvKeranjang = findViewById(R.id.rvKeranjang)
        tvTotalPembayaran = findViewBy       Id(R.id.tvTotalPembayaran)
        btnBayarSekarang = findViewById(R.id.btnBayarSekarang)

        // Mengambil item checkout dari Intent
        val receivedItems = intent.getParcelableArrayListExtra<ItemKeranjang>("EXTRA_KERANJANG")
        if (receivedItems != null) {
            listKeranjang.addAll(receivedItems)
        }

        setupRecyclerView()
        updateTotal()

        btnBayarSekarang.setOnClickListener {
            if (listKeranjang.isEmpty()) {
                Toast.makeText(this, "Keranjang kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, PembayaranActivity::class.java)
                intent.putParcelableArrayListExtra("EXTRA_KERANJANG", ArrayList(listKeranjang))
                intent.putExtra("EXTRA_TOTAL", hitungTotal())
                startActivity(intent)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = KeranjangAdapter(listKeranjang) {
            updateTotal()
        }
        rvKeranjang.layoutManager = LinearLayoutManager(this)
        rvKeranjang.adapter = adapter
    }

    private fun hitungTotal(): Double {
        return listKeranjang.sumOf { it.subtotal }
    }

    private fun updateTotal() {
        val total = hitungTotal()
        tvTotalPembayaran.text = formatRupiah(total)
    }

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number).replace(",00", "")
    }
}