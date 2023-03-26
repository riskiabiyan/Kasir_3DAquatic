package com.riskiabiyan.kasir3daquatic.dataKasir.presenter

import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponse
import com.riskiabiyan.kasir3daquatic.dataKasir.view.GetKasir
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataKasirPresenter() {
    fun getKasir(getKasir: GetKasir){
        RetrofitClient.instance.getKasir().enqueue(object : Callback<KasirResponse>{
            override fun onResponse(call: Call<KasirResponse>, response: Response<KasirResponse>) {
                response.body()?.let { getKasir.onSuccessGetBarang(it) }
            }

            override fun onFailure(call: Call<KasirResponse>, t: Throwable) {
                getKasir.onFailedGetBarang(t.localizedMessage)
            }

        })
    }
}