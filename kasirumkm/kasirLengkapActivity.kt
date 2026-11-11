package com.example.kasirumkm

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.NumberFormat
import java.util.Locale

class kasirLengkapActivity : AppCompatActivity() {

    private lateinit var rvProduk: RecyclerView
    private lateinit var tvTotalBelanja: TextView
    private lateinit var btnKeKeranjang: Button
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var etSearchProduk: EditText

    private val displayedList = mutableListOf<Produk>()
    private lateinit var gridAdapter: ProdukGridAdapter

    companion object {
        val listKeranjang = mutableListOf<ItemKeranjang>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kasir_lengkap)

        initViews()
        setupRecyclerView()
        setupSearch()
        setupBottomNavigation()

        btnKeKeranjang.setOnClickListener {
            syncKeranjangFromAdapter()

            if (listKeranjang.isEmpty()) {
                Toast.makeText(this, "Pilih minimal 1 produk terlebih dahulu!", Toast.LENGTH_SHORT).show()
            } else {
                val totalHarga = listKeranjang.sumOf { it.subtotal }
                val intent = Intent(this, KeranjangActivity::class.java)
                intent.putParcelableArrayListExtra("EXTRA_KERANJANG", ArrayList(listKeranjang))
                intent.putExtra("EXTRA_TOTAL", totalHarga)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        filterProduk(etSearchProduk.text.toString())
        updateTotalBelanja()
    }

    private fun initViews() {
        rvProduk = findViewById(R.id.rvProduk)
        tvTotalBelanja = findViewById(R.id.tvTotalBelanja)
        btnKeKeranjang = findViewById(R.id.btnKeKeranjang)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        etSearchProduk = findViewById(R.id.etSearchProduk)
    }

    private fun setupRecyclerView() {
        displayedList.clear()
        displayedList.addAll(DataProduk.listProduk)

        rvProduk.layoutManager = GridLayoutManager(this, 2)
        gridAdapter = ProdukGridAdapter(displayedList) {
            updateTotalBelanja()
        }
        rvProduk.adapter = gridAdapter
    }

    private fun setupSearch() {
        etSearchProduk.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterProduk(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterProduk(query: String) {
        displayedList.clear()
        if (query.isEmpty()) {
            displayedList.addAll(DataProduk.listProduk)
        } else {
            val filtered = DataProduk.listProduk.filter {
                it.nama.contains(query, ignoreCase = true) || it.id.contains(query, ignoreCase = true)
            }
            displayedList.addAll(filtered)
        }
        gridAdapter.notifyDataSetChanged()
    }

    private fun syncKeranjangFromAdapter() {
        listKeranjang.clear()
        for ((id, jumlah) in gridAdapter.jumlahPilihMap) {
            if (jumlah > 0) {
                val produk = DataProduk.listProduk.find { it.id == id }
                if (produk != null) {
                    listKeranjang.add(ItemKeranjang(produk = produk, jumlah = jumlah))
                }
            }
        }
    }

    private fun updateTotalBelanja() {
        syncKeranjangFromAdapter()
        val total = listKeranjang.sumOf { it.subtotal }
        tvTotalBelanja.text = formatRupiah(total)
    }

    private fun setupBottomNavigation() {
        bottomNavigation.selectedItemId = R.id.nav_home

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_riwayat -> {
                    startActivity(Intent(this, RiwayatActivity::class.java))
                    true
                }
                R.id.nav_laporan -> {
                    startActivity(Intent(this, LaporanActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number).replace(",00", "")
    }
}