package com.riskiabiyan.kasir3daquatic.kategori.view

import com.riskiabiyan.kasir3daquatic.kategori.model.KategoriResponse

interface GetKategori {
    fun onSuccessGetKategori(kategoriResponse: KategoriResponse)
    fun onFailedGetKategori(message:String)
}