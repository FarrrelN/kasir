package com.example.kasirumkm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaksi(
    val idTransaksi: String,
    val tanggal: String,
    val items: List<ItemKeranjang>,
    val totalHarga: Double,
    val metodePembayaran: String,
    val bayar: Double,
    val kembalian: Double
) : Parcelable

object DataTransaksi {
    val listTransaksi = mutableListOf<Transaksi>()
}