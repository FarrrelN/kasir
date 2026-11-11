package com.example.kasirumkm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.Locale

class LaporanFragment : Fragment() {

    private lateinit var tvTotalPendapatan: TextView
    private lateinit var tvTotalTransaksi: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_laporan, container, false)

        tvTotalPendapatan = view.findViewById(R.id.tvTotalPendapatan)
        tvTotalTransaksi = view.findViewById(R.id.tvTotalTransaksi)

        updateLaporan()

        return view
    }

    override fun onResume() {
        super.onResume()
        updateLaporan()
    }

    private fun updateLaporan() {
        val list = DataTransaksi.listTransaksi
        val totalPendapatan = LaporanData.getTotalPendapatan(list)
        val totalTransaksi = LaporanData.getTotalTransaksi(list)

        tvTotalPendapatan.text = formatRupiah(totalPendapatan)
        tvTotalTransaksi.text = "$totalTransaksi Transaksi"
    }

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number).replace(",00", "")
    }
}