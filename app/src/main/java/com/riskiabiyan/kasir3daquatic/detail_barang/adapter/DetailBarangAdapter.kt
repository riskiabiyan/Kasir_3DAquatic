package com.riskiabiyan.kasir3daquatic.detail_barang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.detail_barang.model.DetailBarangResponse
import com.riskiabiyan.kasir3daquatic.detail_barang.model.DetailbarangItem

class DetailBarangAdapter : RecyclerView.Adapter<DetailBarangAdapter.MyHolder>(){
    var dataList = emptyList<DetailbarangItem>()

    internal fun setDataList(dataList: List<DetailbarangItem>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val DBnama_barang : TextView = itemView.findViewById(R.id.tvDBnama_barang)
        val DBketerangan : TextView = itemView.findViewById(R.id.tvDBketerangan)
        val DBharga_modal : TextView = itemView.findViewById(R.id.tvDBharga_modal)
        val DBharga_jual : TextView = itemView.findViewById(R.id.tvDBharga_jual)
        val DBstok : TextView = itemView.findViewById(R.id.tvDBstok)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.detilbarang_item, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val listData = dataList[position]
        holder.DBnama_barang.text = listData.nama_barang
        holder.DBketerangan.text = listData.keterangan
        holder.DBharga_modal.text = listData.harga_modal.toString()
        holder.DBharga_jual.text = listData.harga_jual.toString()
        holder.DBstok.text = listData.stok.toString()

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}