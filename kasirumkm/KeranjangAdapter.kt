package com.example.kasirumkm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class KeranjangAdapter(
    private val listKeranjang: MutableList<ItemKeranjang>,
    private val onItemChanged: () -> Unit
) : RecyclerView.Adapter<KeranjangAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNamaKeranjang)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaKeranjang)
        val tvSubtotal: TextView = view.findViewById(R.id.tvSubtotalKeranjang)
        val btnMinus: Button = view.findViewById(R.id.btnMinusKeranjang)
        val tvJumlah: TextView = view.findViewById(R.id.tvJumlahKeranjang)
        val btnPlus: Button = view.findViewById(R.id.btnPlusKeranjang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_keranjang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listKeranjang[position]

        holder.tvNama.text = item.produk.nama
        holder.tvHarga.text = "@ ${formatRupiah(item.produk.harga)}"
        holder.tvSubtotal.text = formatRupiah(item.subtotal)
        holder.tvJumlah.text = item.jumlah.toString()

        holder.btnPlus.setOnClickListener {
            if (item.jumlah < item.produk.stok) {
                item.jumlah++
                notifyItemChanged(position)
                onItemChanged()
            }
        }

        holder.btnMinus.setOnClickListener {
            if (item.jumlah > 1) {
                item.jumlah--
                notifyItemChanged(position)
                onItemChanged()
            } else {
                // Hapus item dari daftar keranjang jika jumlah dikurangi sampai 0
                listKeranjang.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, listKeranjang.size)
                onItemChanged()
            }
        }
    }

    override fun getItemCount(): Int = listKeranjang.size

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number).replace(",00", "")
    }
}