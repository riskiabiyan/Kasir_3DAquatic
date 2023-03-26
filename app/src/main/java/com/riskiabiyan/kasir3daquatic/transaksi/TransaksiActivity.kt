package com.riskiabiyan.kasir3daquatic.transaksi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.barang.BarangActivity
import com.riskiabiyan.kasir3daquatic.barangMasuk.BarangMasuk
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.databinding.ActivityTransaksiBinding
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponse
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponseItem
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import com.riskiabiyan.kasir3daquatic.lib.FormatCurrency
import com.riskiabiyan.kasir3daquatic.transaksi.`interface`.GetHarga
import com.riskiabiyan.kasir3daquatic.transaksi.`interface`.PostTransaksi
import com.riskiabiyan.kasir3daquatic.transaksi.adapter.TransaksiAdapter
import com.riskiabiyan.kasir3daquatic.transaksi.adapter.TransaksiData
import com.riskiabiyan.kasir3daquatic.transaksi.presenter.TransaksiPresenter

class TransaksiActivity : AppCompatActivity(), GetHarga, PostTransaksi {

    private lateinit var barangkasirResponse: BarangkasirResponse
    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var binding:ActivityTransaksiBinding
    private lateinit var transaksiPresenter: TransaksiPresenter
    private var transaksiData = ArrayList<TransaksiData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        transaksiPresenter = TransaksiPresenter(this)

        val keranjangString = intent.getStringExtra("keranjang_list")
        barangkasirResponse = Gson().fromJson(keranjangString, BarangkasirResponse::class.java)
        setData()

        binding.btnKembalian.setOnClickListener {
            val txt1 = binding.totHargaTrans.text.toString()
            val txt2 = binding.uangmasuk.text.toString()

            if(txt1.isEmpty() || txt2.isEmpty()){
                Toast.makeText(this, "Silahkan di isi dahulu", Toast.LENGTH_LONG).show()
            }else{
                getKembalian()
            }
        }


        val s = (0..1000).shuffled().last()

        binding.bayar.setOnClickListener {
                for(itemSelected in transaksiData) {
                    transaksiPresenter.getTransaksi(
                        itemSelected.id_barang!!,
                        s,
                        itemSelected.hargaTot!!,
                        itemSelected.total!!
                    )
                }

        }
    }

    private fun setData(){
        transaksiAdapter = TransaksiAdapter(this)
        val rv = binding.RecTrans
        rv.adapter = transaksiAdapter
        rv.layoutManager = LinearLayoutManager(this)
        val mtb = barangkasirResponse.data as List<BarangkasirResponseItem>
        transaksiAdapter.setDataList(mtb)

    }

    private fun init() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val drawer_layout: DrawerLayout = findViewById(R.id.drawer_layout)

        setSupportActionBar(toolbar)
        val navView: NavigationView = findViewById(R.id.nav_view)

        navView.setNavigationItemSelectedListener {item->
            when(item.itemId){
                R.id.nav_dashboard ->{
                    startActivity(Intent(this, MainActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_barang ->{
                    startActivity(Intent(this, BarangActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_barangmasuk ->{
                    startActivity(Intent(this, BarangMasuk::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_kasir ->{
                    startActivity(Intent(this, KasirActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_kategori ->{
                    startActivity(Intent(this, KategoriActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                else->{
                    return@setNavigationItemSelectedListener true
                }
            }
        }
        // animasi
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        val toolbar:Toolbar = findViewById(R.id.toolbar)
        val drawer_layout:DrawerLayout = findViewById(R.id.drawer_layout)

        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        } else{
            super.onBackPressed()
        }
    }

    override fun totalHarga(totalHarga: Int, detail: ArrayList<TransaksiData>) {
        binding.totHargaTrans.text = totalHarga.toString()
        this.transaksiData = detail
    }

    override fun onSuccessGetBarangkasir(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }

    private fun getKembalian(){
        val harga1 = binding.totHargaTrans.text.toString().toInt()
        val harga2 = binding.uangmasuk.text.toString().toInt()

        val harga11 = harga2 - harga1


        binding.kembalian.text = FormatCurrency(harga11).formatRupiah()

    }

}