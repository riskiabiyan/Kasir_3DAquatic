package com.riskiabiyan.kasir3daquatic.laporan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponseItem
import com.riskiabiyan.kasir3daquatic.laporan.model.LaporanResponseItem

class LaporanAdapter(): RecyclerView.Adapter<LaporanAdapter.MyHolder>() {

    var datalist = emptyList<LaporanResponseItem>()

    internal fun setDataList(dataList: List<LaporanResponseItem>){
        this.datalist = dataList
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val kode : TextView = itemView.findViewById(R.id.tvLapKode_harga)
        val namalAP : TextView = itemView.findViewById(R.id.tvLapnama_brg)
        val jml : TextView = itemView.findViewById(R.id.tvLapJml_brg)
        val created : TextView = itemView.findViewById(R.id.tvLapCreated)
        val bayar : TextView = itemView.findViewById(R.id.tvLaptotByr)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.laporan_item_post, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val listData = datalist[position]
        holder.kode.text = listData.kode_harga.toString()
        holder.bayar.text = listData.total_bayar.toString()
        holder.created.text = listData.created_at.toString()
        holder.jml.text = listData.jumlah_barang.toString()
        holder.namalAP.text = listData.nama_barang
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}