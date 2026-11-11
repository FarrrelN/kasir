package com.example.kasirumkm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemKeranjang(
    val produk: Produk,
    var jumlah: Int
) : Parcelable {
    val subtotal: Double
        get() = produk.harga * jumlah
}