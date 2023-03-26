package com.riskiabiyan.kasir3daquatic.barangMasuk.adapter

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
import com.riskiabiyan.kasir3daquatic.tambahStok.TambahStok

class BarangMasukAdapter(private val context: Context) : RecyclerView.Adapter<BarangMasukAdapter.MyHolder>() {
    var dataList = emptyList<BarangResponseItem>()

    internal fun setDataList(dataList: List<BarangResponseItem>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val BMpilih : Button = itemView.findViewById(R.id.btnBMtambahstok)
        val BMnama : TextView = itemView.findViewById(R.id.tvBMnama_barang)
        val BMketerangan : TextView = itemView.findViewById(R.id.tvBMKeterangan)
        val BMid : TextView = itemView.findViewById(R.id.tvBMid)
        val BMstok : TextView = itemView.findViewById(R.id.tvBMstok)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.barangmasuk_item, parent, false)
        return MyHolder(view)

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val listData = dataList[position]
        holder.BMnama.text = listData.nama_barang
        holder.BMketerangan.text = listData.keterangan
        holder.BMstok.text = listData.stok.toString()
        holder.BMid.text = listData.id_barang.toString()

        holder.BMpilih.setOnClickListener {
            val listData = dataList[position]
            val nama = listData.nama_barang
            val id = listData.id_barang.toString()
            val stok = listData.stok.toString()

            val intent = Intent(context, TambahStok::class.java)
            intent.putExtra("id", id)
            intent.putExtra("nama", nama)
            intent.putExtra("stok", stok)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}