package com.riskiabiyan.kasir3daquatic.kasir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.barang.BarangActivity
import com.riskiabiyan.kasir3daquatic.barang.adapter.BarangAdapter
import com.riskiabiyan.kasir3daquatic.barang.view.GetBarang
import com.riskiabiyan.kasir3daquatic.barangMasuk.BarangMasuk
import com.riskiabiyan.kasir3daquatic.databinding.ActivityBarangBinding
import com.riskiabiyan.kasir3daquatic.databinding.ActivityKasirBinding
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kasir.adapter.BarangkasirAdapter
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponse
import com.riskiabiyan.kasir3daquatic.kasir.model.BarangkasirResponseItem
import com.riskiabiyan.kasir3daquatic.kasir.presenter.BarangkasirPresenter
import com.riskiabiyan.kasir3daquatic.kasir.view.GetBarangkasir
import com.riskiabiyan.kasir3daquatic.kasir.view.GetCheckbox
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import com.riskiabiyan.kasir3daquatic.transaksi.TransaksiActivity

class KasirActivity : AppCompatActivity(), GetBarangkasir, GetCheckbox{

    private lateinit var binding: ActivityKasirBinding
    private lateinit var barangkasirAdapter: BarangkasirAdapter
    private lateinit var barangkasirPresenter: BarangkasirPresenter
    private var barangkasirResponseItem = mutableListOf<BarangkasirResponseItem>()
    private var selectedCheckbox = BarangkasirResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKasirBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

        barangkasirPresenter = BarangkasirPresenter()
        barangkasirPresenter.getBarangkasir(this)

        binding.selanjutnyaButton.setOnClickListener {

            if(selectedCheckbox.data?.size == 0 || selectedCheckbox.data?.size == null){
                Toast.makeText(this, "Pilih salah satu", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, TransaksiActivity::class.java)
                intent.putExtra("keranjang_list", Gson().toJson(selectedCheckbox))
                startActivity(intent)
            }
        }
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

    override fun onSuccessGetBarangkasir(barangkasirResponse: BarangkasirResponse) {
        barangkasirAdapter = BarangkasirAdapter(this)
        barangkasirResponseItem = barangkasirResponse.data as MutableList<BarangkasirResponseItem>
        var rv = binding.RecKasir
        rv.adapter = barangkasirAdapter
        rv.layoutManager = LinearLayoutManager(this)
        barangkasirAdapter.setDataList(barangkasirResponseItem)
    }

    override fun onFailedGetBarangkasir(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onSuccessDataCheckbox(barangkasirResponse: BarangkasirResponse) {
        selectedCheckbox = barangkasirResponse
    }
}