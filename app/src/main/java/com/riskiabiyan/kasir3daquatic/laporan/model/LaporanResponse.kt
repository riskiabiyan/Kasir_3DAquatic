package com.riskiabiyan.kasir3daquatic.laporan.model

import java.sql.Timestamp

data class LaporanResponse (
    val data : List<LaporanResponseItem>
        )

data class LaporanResponseItem(
    val kode_harga : Int? = null,
    val nama_barang : String? = null,
    val jumlah_barang : Int? = null,
    val total_bayar : Int? = null,
    val created_at : Timestamp? = null
)