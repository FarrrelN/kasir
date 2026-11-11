package com.example.kasirumkm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class LaporanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        val tvTotalPendapatan = findViewById<TextView>(R.id.tvTotalPendapatan)
        val tvTotalTransaksi = findViewById<TextView>(R.id.tvTotalTransaksi)
        val btnKelolaStok = findViewById<Button>(R.id.btnKelolaStok)

        val list = DataTransaksi.listTransaksi
        val totalOmset = LaporanData.getTotalPendapatan(list)
        val jumlahTrx = LaporanData.getTotalTransaksi(list)

        tvTotalPendapatan.text = formatRupiah(totalOmset)
        tvTotalTransaksi.text = "$jumlahTrx Transaksi"

        btnKelolaStok.setOnClickListener {
            startActivity(Intent(this, KelolaStokActivity::class.java))
        }
    }

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number).replace(",00", "")
    }
}