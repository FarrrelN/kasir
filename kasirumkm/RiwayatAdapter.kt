package com.example.kasirumkm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class RiwayatAdapter(
    private val listTransaksi: List<Transaksi>
) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvIdTransaksi: TextView = view.findViewById(R.id.tvIdTransaksi)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val tvMetode: TextView = view.findViewById(R.id.tvMetode)
        val tvTotal: TextView = view.findViewById(R.id.tvTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaksi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listTransaksi[position]
        holder.tvIdTransaksi.text = item.idTransaksi
        holder.tvTanggal.text = item.tanggal
        holder.tvMetode.text = item.metodePembayaran
        holder.tvTotal.text = formatRupiah(item.totalHarga)
    }

    override fun getItemCount(): Int = listTransaksi.size

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number).replace(",00", "")
    }
}