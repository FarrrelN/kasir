package com.example.kasirumkm

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RiwayatActivity : AppCompatActivity() {

    private lateinit var rvRiwayat: RecyclerView
    private lateinit var adapter: TransaksiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        rvRiwayat = findViewById(R.id.rvRiwayat)

        // Gunakan findViewById dengan penanganan null jika tombol kembali opsional
        val btnBack: ImageButton? = findViewById(R.id.btnBack)
        btnBack?.setOnClickListener {
            finish()
        }

        // Setup Adapter
        adapter = TransaksiAdapter(DataTransaksi.listTransaksi)
        rvRiwayat.layoutManager = LinearLayoutManager(this)
        rvRiwayat.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}