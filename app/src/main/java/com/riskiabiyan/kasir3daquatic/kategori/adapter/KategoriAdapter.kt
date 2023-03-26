package com.riskiabiyan.kasir3daquatic.kategori.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.detail_barang.Detailbarang
import com.riskiabiyan.kasir3daquatic.detail_kategori.DetailKategori
import com.riskiabiyan.kasir3daquatic.kategori.TambahKategori
import com.riskiabiyan.kasir3daquatic.kategori.model.KategoriResponseItem

class KategoriAdapter(
    private val context: Context
) : RecyclerView.Adapter<KategoriAdapter.MyHolder>() {

    var dataList = emptyList<KategoriResponseItem>()


    internal fun setDataList(dataList: List<KategoriResponseItem>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val kategoriText: TextView = itemView.findViewById(R.id.tvText)
        val nomorId : TextView = itemView.findViewById(R.id.tvTextId)
        val btnHapus : Button = itemView.findViewById(R.id.btnHapuskategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val listData = dataList[position]
        holder.kategoriText.text = listData.nama_kategori
        holder.nomorId.text = listData.id_kategori.toString()

        holder.btnHapus.setOnClickListener {
            val listData = dataList[position]
            val id = listData.id_kategori.toString()

            val intent = Intent(context, DetailKategori::class.java)
            intent.putExtra("msg", id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}