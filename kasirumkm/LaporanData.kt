package com.example.kasirumkm

object LaporanData {

    fun getTotalPendapatan(listTransaksi: List<Transaksi>): Double {
        return listTransaksi.sumOf { it.totalHarga }
    }

    fun getTotalTransaksi(listTransaksi: List<Transaksi>): Int {
        return listTransaksi.size
    }

    fun getPendapatanPerMetode(listTransaksi: List<Transaksi>, metode: String): Double {
        return listTransaksi
            .filter { it.metodePembayaran.equals(metode, ignoreCase = true) }
            .sumOf { it.totalHarga }
    }
}