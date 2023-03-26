package com.riskiabiyan.kasir3daquatic.kasir.view

import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponse

interface GetBarangkasir {
    fun onSuccessGetBarangkasir(barangkasirResponse: BarangkasirResponse)
    fun onFailedGetBarangkasir(message:String)
}