package com.riskiabiyan.kasir3daquatic.barang.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponseItem
import com.riskiabiyan.kasir3daquatic.detail_barang.Detailbarang

class BarangAdapter(
    private val context: Context
) : RecyclerView.Adapter<BarangAdapter.MyHolder>() {

    var dataList = emptyList<BarangResponseItem>()

    internal fun setDataList(dataList: List<BarangResponseItem>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val barangText : TextView = itemView.findViewById(R.id.tvTextnama_barang)
        val keteranganText : TextView = itemView.findViewById(R.id.tvTextketerangan)
        val idText : TextView = itemView.findViewById(R.id.tvTextid)
        val btnLihat : Button = itemView.findViewById(R.id.btnLihatDetilBarang)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.barang_item_post, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val listData = dataList[position]
        holder.barangText.text = listData.nama_barang
        holder.keteranganText.text = listData.keterangan
        holder.idText.text = listData.id_barang.toString()

        holder.btnLihat.setOnClickListener {
            val listData = dataList[position]
            val id = listData.id_barang.toString()

            val intent = Intent(context, Detailbarang::class.java)
            intent.putExtra("msg", id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}