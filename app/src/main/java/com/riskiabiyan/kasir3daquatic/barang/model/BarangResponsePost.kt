package com.riskiabiyan.kasir3daquatic.barang.model

data class BarangResponsePost(
    val id_barang : Int? = null,
    val kategori_id : Int? = null,
    val nama_barang : String? = null,
    val keterangan : String? = null,
    val harga_awal : Int? = null,
    val harga_jual : Int? = null,
    val stok : Int? = null
)