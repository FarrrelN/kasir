package com.example.kasirumkm

object Gudang {
    val listProduk = mutableListOf<Produk>(
        Produk("P01", "Minyak Goreng 1L", 18000.0, 10),
        Produk("P02", "Gula Pasir 1kg", 16000.0, 15),
        Produk("P03", "Kopi Kapal Api", 3000.0, 50),
        Produk("P04", "Indomie Goreng", 3500.0, 40)
    )

    fun tambahProduk(produk: Produk) {
        listProduk.add(produk)
    }

    fun tambahProduk(nama: String, harga: Double, stokAwal: Int) {
        val idBaru = "P" + (listProduk.size + 1).toString().padStart(2, '0')
        listProduk.add(Produk(idBaru, nama, harga, stokAwal))
    }

    fun restockProduk(idProduk: String, jumlahTambah: Int): Boolean {
        val produk = listProduk.find { it.id == idProduk }
        return if (produk != null) {
            produk.stok += jumlahTambah
            true
        } else {
            false
        }
    }

    fun kurangiStok(idProduk: String, jumlah: Int): Boolean {
        val produk = listProduk.find { it.id == idProduk }
        return if (produk != null && produk.stok >= jumlah) {
            produk.stok -= jumlah
            true
        } else {
            false
        }
    }
}