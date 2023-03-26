package com.riskiabiyan.kasir3daquatic.api

import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponse
import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponsePost
import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponse
import com.riskiabiyan.kasir3daquatic.detail_barang.model.DetailBarangResponse
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponse

import com.riskiabiyan.kasir3daquatic.kategori.model.KategoriResponse
import com.riskiabiyan.kasir3daquatic.kategori.model.KategoriResponsePost
import com.riskiabiyan.kasir3daquatic.laporan.model.LaporanResponse
import com.riskiabiyan.kasir3daquatic.login.model.LoginResponse
import com.riskiabiyan.kasir3daquatic.tambahStok.model.TambahStokResponse
import com.riskiabiyan.kasir3daquatic.transaksi.model.TransaksiResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiKasir {

    @FormUrlEncoded
    @POST("kategori/add")
    fun createKategori(
        @Field("nama_kategori") nama_kategori: String
    ): Call<KategoriResponsePost>

    @FormUrlEncoded
    @POST("transaksi/add")
    fun createTransaksi(
        @Field("id_user") id_user : Int,
        @Field("id_barang") id_barang : Int,
        @Field("total_bayar") total_bayar : Int,
        @Field("kode_harga") kode_harga : Int,
        @Field("jumlah_barang") jumlah_barang: Int
    ) : Call<TransaksiResponse>

    @FormUrlEncoded
    @POST("users/add")
    fun createKasir(
        @Field("nama_user") nama_user : String,
        @Field("role") role : String,
        @Field("email") email : String,
        @Field("no_hp") no_hp : String,
        @Field("password") password: String
    ) : Call<KasirResponse>

    @FormUrlEncoded
    @POST("barang/add")
    fun createBarang(
        @Field("id_kategori") id_kategori : Int,
        @Field("nama_barang") nama_barang : String,
        @Field("keterangan") keterangana : String,
        @Field("harga_modal") harga_modal : Int,
        @Field("harga_jual") harga_jual : Int,
        @Field("stok") stok : Int
    ) : Call<BarangResponsePost>

    @GET("laporan")
    fun getLaporan(): Call<LaporanResponse>

    @GET("kategori")
    fun getKategori(): Call<KategoriResponse>

    @GET("kasir/get")
    fun getKasir(): Call<KasirResponse>

    @GET("barang")
    fun getBarang(): Call<BarangResponse>

    @GET("barang/kasir")
    fun getBarangKasir(): Call<BarangkasirResponse>

    @GET("barang/detail/{id_barang}")
    fun getDetilBarang(@Path("id_barang") id_barang: Int) : Call<DetailBarangResponse>

    @DELETE("kategori/delete/{id_kategori}")
    fun deleteKategori(@Path("id_kategori") id_kategori: Int) :Call<Void>

    @DELETE("barang/delete/{id_barang}")
    fun deleteBarang(@Path("id_barang") id_barang: Int) :Call<Void>

    @FormUrlEncoded
    @POST("barang/update_stok")
    fun update_stok(
        @Field("id_barang") id_barang: Int,
        @Field("stok") stok : Int
    ) : Call<TambahStokResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("role") role : String,
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<LoginResponse>



}