package com.riskiabiyan.kasir3daquatic.detail_barang.view

import com.riskiabiyan.kasir3daquatic.detail_barang.model.DetailBarangResponse

interface GetDetailBarang {

        fun onSuccessGetBarang(detailBarangResponse: DetailBarangResponse)
        fun onFailedGetBarang(message:String)

}