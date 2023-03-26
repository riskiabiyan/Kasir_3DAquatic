package com.riskiabiyan.kasir3daquatic.kasir.presenter

import android.util.Log
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponse
import com.riskiabiyan.kasir3daquatic.kasir.view.GetBarangkasir
import retrofit2.Call
import retrofit2.Response

class BarangkasirPresenter() {
    fun getBarangkasir(getBarangkasir: GetBarangkasir){
        RetrofitClient.instance.getBarangKasir().enqueue(object : retrofit2.Callback<BarangkasirResponse>{
            override fun onResponse(
                call: Call<BarangkasirResponse>,
                response: Response<BarangkasirResponse>
            ) {
                Log.e("Message", response.body().toString())
                response.body()?.let { getBarangkasir.onSuccessGetBarangkasir(it) }
            }

            override fun onFailure(call: Call<BarangkasirResponse>, t: Throwable) {
                Log.e("Message", t.localizedMessage)
                getBarangkasir.onFailedGetBarangkasir(t.localizedMessage)
            }

        })
    }
}