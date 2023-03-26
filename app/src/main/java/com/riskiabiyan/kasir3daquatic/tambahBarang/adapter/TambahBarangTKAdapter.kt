package com.riskiabiyan.kasir3daquatic.tambahBarang.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.barang.view.TambahBarang
import com.riskiabiyan.kasir3daquatic.kategori.adapter.KategoriAdapter
import com.riskiabiyan.kasir3daquatic.kategori.model.KategoriResponseItem

class TambahBarangTKAdapter(
    private val context: Context
    ) : RecyclerView.Adapter<TambahBarangTKAdapter.MyHolder>(){

    var datalist = emptyList<KategoriResponseItem>()

    internal fun setDataList(dataList: List<KategoriResponseItem>){
        this.datalist = dataList
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val TBnama_kategori : TextView = itemView.findViewById(R.id.tvTBnama_kategori)
        val TBid : TextView = itemView.findViewById(R.id.tvTBid)
        val TBbtn : Button = itemView.findViewById(R.id.btnTBpilih)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TambahBarangTKAdapter.MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tampilkategori_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: TambahBarangTKAdapter.MyHolder, position: Int) {
        val listData = datalist[position]
        holder.TBnama_kategori.text = listData.nama_kategori
        holder.TBid.text = listData.id_kategori.toString()
        holder.TBbtn.setOnClickListener {
            val listData = datalist[position]
            val id = listData.id_kategori.toString()

            val intent = Intent(context, TambahBarang::class.java)
            intent.putExtra("msg", id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

}