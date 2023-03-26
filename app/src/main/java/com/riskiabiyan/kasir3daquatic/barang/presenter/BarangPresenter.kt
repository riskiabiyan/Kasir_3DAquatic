package com.riskiabiyan.kasir3daquatic.barang.presenter

import android.util.Log
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponse
import com.riskiabiyan.kasir3daquatic.barang.view.GetBarang
import retrofit2.Call
import retrofit2.Response

class BarangPresenter() {

    fun getBarang(getBarang: GetBarang){
        RetrofitClient.instance.getBarang().enqueue(object : retrofit2.Callback<BarangResponse>{
            override fun onResponse(
                call: Call<BarangResponse>,
                response: Response<BarangResponse>
            ) {
                Log.e("Message", response.body().toString())
                response.body()?.let { getBarang.onSuccessGetBarang(it) }
            }

            override fun onFailure(call: Call<BarangResponse>, t: Throwable) {
                Log.e("Message", t.localizedMessage)
                getBarang.onFailedGetBarang(t.localizedMessage)
            }
        })
    }
}