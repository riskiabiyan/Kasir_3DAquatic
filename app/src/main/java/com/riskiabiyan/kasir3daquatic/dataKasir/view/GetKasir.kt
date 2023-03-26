package com.riskiabiyan.kasir3daquatic.dataKasir.view


import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponse

interface GetKasir {
    fun onSuccessGetBarang(kasirResponse: KasirResponse)
    fun onFailedGetBarang(message:String)
}