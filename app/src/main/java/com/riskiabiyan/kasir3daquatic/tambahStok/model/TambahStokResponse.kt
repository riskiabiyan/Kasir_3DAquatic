package com.riskiabiyan.kasir3daquatic.tambahStok.model

data class TambahStokResponse (
    val data : List<TambahStokResponseItem?>? = null
        )

data class TambahStokResponseItem(
    val id_barang: Int? = null,
    val nama_barang: String? = null,
    val stok : Int? = null
)