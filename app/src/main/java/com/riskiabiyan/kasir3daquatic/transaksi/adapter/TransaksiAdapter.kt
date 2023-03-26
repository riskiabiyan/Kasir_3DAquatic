package com.riskiabiyan.kasir3daquatic.transaksi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponseItem
import com.riskiabiyan.kasir3daquatic.lib.FormatCurrency
import com.riskiabiyan.kasir3daquatic.transaksi.*
import com.riskiabiyan.kasir3daquatic.transaksi.`interface`.GetHarga

data class TransaksiData(
    var index: Int?=null,
    var hargaTot: Int?=null,
    var total: Int?=null,
    var id_barang: Int?=null
)

class TransaksiAdapter(
    private val getHarga: GetHarga
) : RecyclerView.Adapter<TransaksiAdapter.MyHolder>() {

    var dataList = emptyList<BarangkasirResponseItem>()
    var transaksiData = ArrayList<TransaksiData>()

    internal fun setDataList(dataList: List<BarangkasirResponseItem>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val namaBarang : TextView = itemView.findViewById(R.id.tvTransnama_barang)
        val jmlhBarang : EditText = itemView.findViewById(R.id.jumlah)
        val harga: TextView = itemView.findViewById(R.id.tvTransharga_jual)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.barang_trans_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val listData = dataList[position]
        holder.namaBarang.text = listData.nama_barang
        holder.harga.text = FormatCurrency(listData.harga_jual).formatRupiah()
        holder.jmlhBarang.doAfterTextChanged { it->
            val inputJumlah = if(it.toString().isEmpty()){0}else{it}
            val hargaSingle = inputJumlah.toString().toInt() * listData.harga_jual!!

            try {
                if(position == transaksiData[position].index){
                    // ada
                    transaksiData[position].hargaTot = hargaSingle
                    transaksiData[position].total = it.toString().toInt()
                    transaksiData[position].id_barang = listData.id_barang
                }else{
                    // gak ada
                    transaksiData.add(position, TransaksiData(position, hargaSingle, it.toString().toInt(), listData.id_barang))
                }

            } catch (e: IndexOutOfBoundsException) {
                // gak ada
                transaksiData.add(position, TransaksiData(position, hargaSingle))
                transaksiData[position].total = it.toString().toInt()
                transaksiData[position].id_barang = listData.id_barang
            }

            getHarga.totalHarga(totalHarga(), transaksiData)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun totalHarga():Int{
        var harga:Int = 0
        for (item in transaksiData){
            harga = harga + item.hargaTot!!
        }
        return harga
    }

}