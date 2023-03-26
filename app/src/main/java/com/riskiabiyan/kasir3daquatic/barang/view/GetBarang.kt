package com.riskiabiyan.kasir3daquatic.barang.view

import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponse

interface GetBarang {
    fun onSuccessGetBarang(barangResponse: BarangResponse)
    fun onFailedGetBarang(message:String)
}