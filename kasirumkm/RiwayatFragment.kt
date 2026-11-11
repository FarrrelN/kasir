package com.example.kasirumkm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RiwayatFragment : Fragment() {

    private lateinit var rvRiwayat: RecyclerView
    private lateinit var adapter: RiwayatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_riwayat, container, false)

        rvRiwayat = view.findViewById(R.id.rvRiwayat)

        // Menggunakan daftar transaksi dari singleton DataTransaksi
        adapter = RiwayatAdapter(DataTransaksi.listTransaksi)

        rvRiwayat.layoutManager = LinearLayoutManager(requireContext())
        rvRiwayat.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        // Memperbarui daftar tampilan ketika kembali ke fragment ini
        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        }
    }
}