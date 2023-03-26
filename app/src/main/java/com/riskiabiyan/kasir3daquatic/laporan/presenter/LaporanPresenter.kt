package com.riskiabiyan.kasir3daquatic.laporan.presenter

import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.laporan.model.LaporanResponse
import com.riskiabiyan.kasir3daquatic.laporan.view.GetLaporan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaporanPresenter() {
    fun getLaporan(getLaporan: GetLaporan){
        RetrofitClient.instance.getLaporan().enqueue(object : Callback<LaporanResponse>{
            override fun onResponse(
                call: Call<LaporanResponse>,
                response: Response<LaporanResponse>
            ) {
                response.body().let { it?.let { it1 -> getLaporan.onSuccessGetBarang(it1) } }
            }

            override fun onFailure(call: Call<LaporanResponse>, t: Throwable) {
                getLaporan.onFailedGetBarang(t.localizedMessage)
            }

        })
    }
}