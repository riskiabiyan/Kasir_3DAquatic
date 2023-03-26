package com.riskiabiyan.kasir3daquatic.kasir.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponse
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponseItem
import com.riskiabiyan.kasir3daquatic.kasir.view.GetCheckbox

class BarangkasirAdapter(
    private val getCheckbox: GetCheckbox
) : RecyclerView.Adapter<BarangkasirAdapter.MyHolder>() {

    var dataList = emptyList<BarangkasirResponseItem>()
    var selectedCheckbox = BarangkasirResponse()
    var selectedItemCheckbox = ArrayList<BarangkasirResponseItem>()

    internal fun setDataList(dataList: List<BarangkasirResponseItem>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val namaKategoriText : TextView = itemView.findViewById(R.id.tvKasirnama_kategori)
        val barangText : TextView = itemView.findViewById(R.id.tvKasirnama_barang)
        val hargaText : TextView = itemView.findViewById(R.id.tvKasirharga_jual)
        val keteranganText : TextView = itemView.findViewById(R.id.tvKasirketerangan)
        val stokText : TextView = itemView.findViewById(R.id.tvKasirstok)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.barangkasir_item_post, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val ListData = dataList[position]
        holder.namaKategoriText.text = ListData.nama_kategori
        holder.barangText.text = ListData.nama_barang
        holder.hargaText.text = ListData.harga_jual.toString()
        holder.stokText.text = ListData.stok.toString()
        holder.keteranganText.text = ListData.keterangan
        holder.checkBox.setOnClickListener {
            if(holder.checkBox.isChecked){
                selectedItemCheckbox.add(
                    BarangkasirResponseItem(
                        ListData.id_barang,
                        ListData.nama_kategori,
                        ListData.updated_at,
                        ListData.created_at,
                        ListData.id_kategori,
                        ListData.nama_barang,
                        ListData.keterangan,
                        ListData.harga_jual,
                    )
                )
                selectedCheckbox.data = selectedItemCheckbox
                getCheckbox.onSuccessDataCheckbox(selectedCheckbox)
            }else{
                selectedItemCheckbox.remove(
                    BarangkasirResponseItem(
                        ListData.id_barang,
                        ListData.nama_kategori,
                        ListData.updated_at,
                        ListData.created_at,
                        ListData.id_kategori,
                        ListData.nama_barang,
                        ListData.keterangan,
                        ListData.harga_jual,
                    )
                )
                selectedCheckbox.data = selectedItemCheckbox
                getCheckbox.onSuccessDataCheckbox(selectedCheckbox)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}