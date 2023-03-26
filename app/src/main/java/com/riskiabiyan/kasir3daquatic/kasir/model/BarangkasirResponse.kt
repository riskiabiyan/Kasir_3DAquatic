package com.riskiabiyan.kasir3daquatic.kasir.model

data class BarangkasirResponse (
    var data: List<BarangkasirResponseItem?>? = null
)

data class BarangkasirResponseItem(
    val id_barang: Int? = null,
    val nama_kategori: String? = null,
    val updated_at: String? = null,
    val created_at: String? = null,
    val id_kategori: Int? = null,
    val nama_barang: String? = null,
    val keterangan: String? = null,
    val harga_jual: Int? = null,
    val stok: Int? = null
)