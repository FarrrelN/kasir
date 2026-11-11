package com.example.kasirumkm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class KelolaProdukAdapter(
    private val listProduk: List<Produk>,
    private val onRestockClick: (Produk) -> Unit,
    private val onDeleteClick: (Produk) -> Unit
) : RecyclerView.Adapter<KelolaProdukAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaProduk: TextView = itemView.findViewById(R.id.tvNamaProduk)
        val tvHargaProduk: TextView = itemView.findViewById(R.id.tvHargaProduk)
        val tvStokProduk: TextView = itemView.findViewById(R.id.tvStokProduk)
        val btnRestock: Button? = itemView.findViewById(R.id.btnRestock)
        val btnHapus: Button? = itemView.findViewById(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = listProduk[position]

        holder.tvNamaProduk.text = produk.nama
        holder.tvHargaProduk.text = formatRupiah(produk.harga)
        holder.tvStokProduk.text = "Stok: ${produk.stok}"

        holder.btnRestock?.setOnClickListener {
            onRestockClick(produk)
        }

        holder.btnHapus?.setOnClickListener {
            onDeleteClick(produk)
        }
    }

    override fun getItemCount(): Int = listProduk.size

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number).replace(",00", "")
    }
}