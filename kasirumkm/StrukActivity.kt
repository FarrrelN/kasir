package com.example.kasirumkm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StrukActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_struk)

        val tvDetail = findViewById<TextView>(R.id.tvStokDetail)
        val btnSelesai = findViewById<Button>(R.id.btnSelesai)

        val transaksi = intent.getParcelableExtra<Transaksi>("EXTRA_TRANSAKSI")

        if (transaksi != null) {
            val sb = StringBuilder()
            sb.append("ID TRX  : ${transaksi.idTransaksi}\n")
            sb.append("Tanggal : ${transaksi.tanggal}\n")
            sb.append("Metode  : ${transaksi.metodePembayaran}\n")
            sb.append("--------------------------------\n")
            for (item in transaksi.items) {
                sb.append("${item.produk.nama}\n")
                sb.append("  ${item.jumlah} x Rp${item.produk.harga.toLong()} = Rp${item.subtotal.toLong()}\n")
            }
            sb.append("--------------------------------\n")
            sb.append("TOTAL     : Rp${transaksi.totalHarga.toLong()}\n")
            sb.append("BAYAR     : Rp${transaksi.bayar.toLong()}\n")
            sb.append("KEMBALIAN : Rp${transaksi.kembalian.toLong()}\n")
            sb.append("--------------------------------\n")
            sb.append("   Terima Kasih atas Kunjungan Anda!   ")

            tvDetail.text = sb.toString()
        }

        btnSelesai.setOnClickListener {
            val intent = Intent(this, kasirLengkapActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}