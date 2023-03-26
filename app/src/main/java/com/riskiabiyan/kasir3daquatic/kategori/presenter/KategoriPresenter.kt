package com.riskiabiyan.kasir3daquatic.kategori.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.kategori.model.KategoriResponse
import com.riskiabiyan.kasir3daquatic.kategori.view.GetKategori
import retrofit2.Call
import retrofit2.Response

class KategoriPresenter() {

    fun getKategori(getKategori: GetKategori){
        RetrofitClient.instance.getKategori()
            .enqueue(object : retrofit2.Callback<KategoriResponse>{
                override fun onResponse(
                    call: Call<KategoriResponse>,
                    response: Response<KategoriResponse>
                ) {
                    Log.e("Haha", response.body().toString())
                    response.body()?.let { getKategori.onSuccessGetKategori(it) }
                }

                override fun onFailure(call: Call<KategoriResponse>, t: Throwable) {
                    Log.e("Haha", t.localizedMessage)
                    getKategori.onFailedGetKategori(t.localizedMessage)
                }

            })

    }

}