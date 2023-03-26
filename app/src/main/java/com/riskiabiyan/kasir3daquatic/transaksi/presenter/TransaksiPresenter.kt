package com.riskiabiyan.kasir3daquatic.transaksi.presenter

import android.util.Log
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.transaksi.`interface`.PostTransaksi
import com.riskiabiyan.kasir3daquatic.transaksi.model.TransaksiResponse
import retrofit2.Call
import retrofit2.Response

class TransaksiPresenter(val postTransaksi: PostTransaksi) {
    fun getTransaksi(

        id_barang: Int, kode_harga: Int, total_bayar: Int, jumlah_barang:Int
    ){
        RetrofitClient.instance.createTransaksi(
            11,
            id_barang,  total_bayar, kode_harga, jumlah_barang
        ).enqueue(object : retrofit2.Callback<TransaksiResponse>{
            override fun onResponse(
                call: Call<TransaksiResponse>,
                response: Response<TransaksiResponse>
            ) {
                Log.e("Code", response.code().toString())
                response.body()?.let { postTransaksi.onSuccessGetBarangkasir(it.message) }
            }

            override fun onFailure(call: Call<TransaksiResponse>, t: Throwable) {
                // internet jelek
                postTransaksi.onSuccessGetBarangkasir(t.localizedMessage)
            }

        })
    }
}