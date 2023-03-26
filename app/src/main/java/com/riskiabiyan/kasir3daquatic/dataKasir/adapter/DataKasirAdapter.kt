package com.riskiabiyan.kasir3daquatic.dataKasir.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponseItem

class DataKasirAdapter():RecyclerView.Adapter<DataKasirAdapter.MyHolder>() {

    var dataList = emptyList<KasirResponseItem>()

    internal fun setDataList(dataList: List<KasirResponseItem>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nama : TextView = itemView.findViewById(R.id.tvDKnama_user)
        val email : TextView = itemView.findViewById(R.id.tvDKemail)
        val noHp : TextView = itemView.findViewById(R.id.tvDKno_hp)
        val Kasir : TextView = itemView.findViewById(R.id.tvDKkasir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.datakasir_item, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val listData = dataList[position]
        holder.nama.text = listData.nama_user
        holder.email.text = listData.email
        holder.noHp.text = listData.no_hp
        holder.Kasir.text = listData.role
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}