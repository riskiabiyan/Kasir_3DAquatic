package com.riskiabiyan.kasir3daquatic.detail_barang.model

data class DetailBarangResponse(
    val data : List<DetailbarangItem?>? = null
)

data class DetailbarangItem(
    val id_barang: Int? = null,
    val updated_at: String? = null,
    val created_at: String? = null,
    val id_kategori: Int? = null,
    val nama_barang: String? = null,
    val keterangan: String? = null,
    val harga_jual: Int? = null,
    val harga_modal: Int? = null,
    val stok: Int? = null
)