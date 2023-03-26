package com.riskiabiyan.kasir3daquatic.transaksi.`interface`

import com.riskiabiyan.kasir3daquatic.transaksi.adapter.TransaksiData

interface GetHarga {
    fun totalHarga(totalHarga: Int, detail: ArrayList<TransaksiData>)
}