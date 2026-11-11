package com.example.kasirumkm

object DataProduk {

    val defaultIcon = android.R.drawable.ic_menu_report_image

    val listProduk = mutableListOf<Produk>(
        Produk("S01", "Beras Setra Ramos 5kg", 72000.0, 30, defaultIcon),
        Produk("S02", "Minyak Goreng Sania 2L", 36500.0, 25, defaultIcon),
        Produk("S03", "Gula Pasir Gulaku 1kg", 17500.0, 40, defaultIcon),
        Produk("S04", "Indomie Goreng Spesial 85g", 3500.0, 150, defaultIcon),
        Produk("S05", "Air Mineral Aqua 600ml", 4000.0, 200, defaultIcon)
    )

    fun kurangiStok(id: String, jumlah: Int): Boolean {
        val produk = listProduk.find { it.id == id }
        return if (produk != null && produk.stok >= jumlah) {
            produk.stok -= jumlah
            true
        } else {
            false
        }
    }

    // Fungsi Tambah Stok Produk (Restok)
    fun tambahStok(id: String, tambahanStok: Int) {
        val produk = listProduk.find { it.id == id }
        produk?.let {
            it.stok += tambahanStok
        }
    }
}