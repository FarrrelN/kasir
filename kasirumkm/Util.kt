package com.example.kasirumkm

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Ubah angka 15000 menjadi "Rp15.000" */
fun rupiah(angka: Long): String {
    val teks = angka.toString().reversed().chunked(3).joinToString(".").reversed()
    return "Rp$teks"
}

/** Ubah waktu (milidetik) menjadi "15/09/2026 14:30" */
fun tanggal(waktu: Long): String {
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("id", "ID"))
    return format.format(Date(waktu))
}

/** Cek apakah waktu tersebut masih di hari yang sama dengan hari ini */
fun hariIni(waktu: Long): Boolean {
    val format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    return format.format(Date(waktu)) == format.format(Date())
}
