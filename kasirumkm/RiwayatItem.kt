package com.example.kasirumkm

data class RiwayatItem(
    val id: String,
    val tanggal: String,
    val total: Double, // <-- HARUS BACA DOUBLE DI SINI!
    val metodePembayaran: String
)