package com.example.kasirumkm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Produk(
    val id: String,
    var nama: String,
    var harga: Double,
    var stok: Int,
    var gambarResId: Int = android.R.drawable.ic_menu_report_image
) : Parcelable