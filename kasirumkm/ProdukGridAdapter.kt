package com.example.kasirumkm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class ProdukGridAdapter(
    private val listProduk: List<Produk>,
    private val onJumlahChanged: () -> Unit
) : RecyclerView.Adapter<ProdukGridAdapter.ViewHolder>() {

    // Menyimpan jumlah pilih sementara untuk tiap ID produk
    val jumlahPilihMap = mutableMapOf<String, Int>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivGambar: ImageView = view.findViewById(R.id.ivGambar)
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvHarga: TextView = view.findViewById(R.id.tvHarga)
        val tvStok: TextView = view.findViewById(R.id.tvStok)
        val btnMinus: Button = view.findViewById(R.id.btnMinus)
        val tvJumlah: TextView = view.findViewById(R.id.tvJumlah)
        val btnPlus: Button = view.findViewById(R.id.btnPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produk_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = listProduk[position]

        holder.tvNama.text = produk.nama
        holder.tvHarga.text = formatRupiah(produk.harga)
        holder.tvStok.text = "Stok: ${produk.stok}"


        val jumlahSaatIni = jumlahPilihMap[produk.id] ?: 0
        holder.tvJumlah.text = jumlahSaatIni.toString()

        holder.btnPlus.setOnClickListener {
            val current = jumlahPilihMap[produk.id] ?: 0
            if (current < produk.stok) {
                val newCount = current + 1
                jumlahPilihMap[produk.id] = newCount
                holder.tvJumlah.text = newCount.toString()
                onJumlahChanged()
            } else {
                Toast.makeText(holder.itemView.context, "Stok tidak mencukupi!", Toast.LENGTH_SHORT).show()
            }
        }

        holder.btnMinus.setOnClickListener {
            val current = jumlahPilihMap[produk.id] ?: 0
            if (current > 0) {
                val newCount = current - 1
                if (newCount == 0) {
                    jumlahPilihMap.remove(produk.id)
                } else {
                    jumlahPilihMap[produk.id] = newCount
                }
                holder.tvJumlah.text = newCount.toString()
                onJumlahChanged()
            }
        }
    }

    override fun getItemCount(): Int = listProduk.size

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number).replace(",00", "")
    }
}