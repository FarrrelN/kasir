package com.example.kasirumkm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class KasirFragment : Fragment() {

    private lateinit var rvProduk: RecyclerView
    private val keranjangList = mutableListOf<ItemKeranjang>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menghubungkan Kotlin dengan layout fragment_kasir.xml
        return inflater.inflate(R.layout.fragment_kasir, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView dari ID xml
        rvProduk = view.findViewById(R.id.rvProduk)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Mengambil daftar produk dari Singleton Gudang
        val listProduk: List<Produk> = Gudang.listProduk

        rvProduk.layoutManager = LinearLayoutManager(requireContext())

        // Memasang adapter dengan callback click item
        rvProduk.adapter = ProdukAdapter(listProduk) { produkDipilih ->
            tambahKeKeranjang(produkDipilih)
        }
    }

    private fun tambahKeKeranjang(produk: Produk) {
        if (produk.stok <= 0) {
            Toast.makeText(
                requireContext(),
                "Stok ${produk.nama} habis!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Cek apakah item sudah ada di keranjang
        val itemAda = keranjangList.find { it.produk.id == produk.id }
        if (itemAda != null) {
            itemAda.jumlah += 1
        } else {
            keranjangList.add(ItemKeranjang(produk = produk, jumlah = 1))
        }

        Toast.makeText(
            requireContext(),
            "${produk.nama} ditambahkan ke keranjang",
            Toast.LENGTH_SHORT
        ).show()
    }
}