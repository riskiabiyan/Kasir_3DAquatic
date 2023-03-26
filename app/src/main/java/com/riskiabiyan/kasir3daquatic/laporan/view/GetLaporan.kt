package com.riskiabiyan.kasir3daquatic.laporan.view

import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponse
import com.riskiabiyan.kasir3daquatic.laporan.model.LaporanResponse

interface GetLaporan {
    fun onSuccessGetBarang(laporanResponse: LaporanResponse)
    fun onFailedGetBarang(message:String)
}