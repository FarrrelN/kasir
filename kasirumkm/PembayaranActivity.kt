package com.example.kasirumkm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PembayaranActivity : AppCompatActivity() {

    private lateinit var tvTotalBayar: TextView
    private lateinit var rgMetodePembayaran: RadioGroup
    private lateinit var etUangDiterima: EditText
    private lateinit var btnProsesBayar: Button

    private var listKeranjang = ArrayList<ItemKeranjang>()
    private var totalHarga = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        tvTotalBayar = findViewById(R.id.tvTotalBayar)
        rgMetodePembayaran = findViewById(R.id.rgMetodePembayaran)
        etUangDiterima = findViewById(R.id.etUangDiterima)
        btnProsesBayar = findViewById(R.id.btnProsesBayar)

        listKeranjang = intent.getParcelableArrayListExtra("EXTRA_KERANJANG") ?: arrayListOf()
        totalHarga = intent.getDoubleExtra("EXTRA_TOTAL", 0.0)

        tvTotalBayar.text = "Total: Rp ${totalHarga.toLong()}"

        rgMetodePembayaran.setOnCheckedChangeListener { _, checkedId ->
            val isTunai = checkedId == R.id.rbTunai
            etUangDiterima.isEnabled = isTunai
            if (!isTunai) {
                etUangDiterima.setText(totalHarga.toLong().toString())
            }
        }

        btnProsesBayar.setOnClickListener {
            prosesTransaksi()
        }
    }

    private fun prosesTransaksi() {
        val selectedRadioId = rgMetodePembayaran.checkedRadioButtonId
        val radioSelected = findViewById<RadioButton>(selectedRadioId)
        val metode = radioSelected?.text?.toString() ?: "Cash / Tunai"

        val uangInputText = etUangDiterima.text.toString()
        val uangDiterima = uangInputText.toDoubleOrNull() ?: totalHarga

        if (uangDiterima < totalHarga) {
            Toast.makeText(this, "Uang pembayaran kurang!", Toast.LENGTH_SHORT).show()
            return
        }

        val kembalian = uangDiterima - totalHarga

        // 1. Potong Stok Produk
        for (item in listKeranjang) {
            DataProduk.kurangiStok(item.produk.id, item.jumlah)
        }

        // 2. Simpan Riwayat Transaksi
        val timeStamp = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        val transaksi = Transaksi(
            idTransaksi = "TRX-${System.currentTimeMillis().toString().takeLast(6)}",
            tanggal = timeStamp,
            items = listKeranjang,
            totalHarga = totalHarga,
            metodePembayaran = metode,
            bayar = uangDiterima,
            kembalian = kembalian
        )
        DataTransaksi.listTransaksi.add(0, transaksi)

        // 3. Pindah ke Struk Activity
        val intent = Intent(this, StrukActivity::class.java)
        intent.putExtra("EXTRA_TRANSAKSI", transaksi)
        startActivity(intent)
        finish()
    }
}