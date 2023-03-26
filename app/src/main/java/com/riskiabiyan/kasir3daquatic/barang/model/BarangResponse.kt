package com.riskiabiyan.kasir3daquatic.barang.model

data class BarangResponse (
    val data: List<BarangResponseItem?>? = null
        )

data class BarangResponseItem(
    val id_barang: Int? = null,
    val updated_at: String? = null,
    val created_at: String? = null,
    val id_kategori: Int? = null,
    val nama_barang: String? = null,
    val keterangan: String? = null,
    val harga_jual: Int? = null,
    val stok: Int? = null
)