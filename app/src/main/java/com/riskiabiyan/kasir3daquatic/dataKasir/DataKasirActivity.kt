package com.riskiabiyan.kasir3daquatic.dataKasir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.barang.BarangActivity
import com.riskiabiyan.kasir3daquatic.barangMasuk.BarangMasuk
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.dataKasir.adapter.DataKasirAdapter
import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponse
import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponseItem
import com.riskiabiyan.kasir3daquatic.dataKasir.presenter.DataKasirPresenter
import com.riskiabiyan.kasir3daquatic.dataKasir.view.GetKasir
import com.riskiabiyan.kasir3daquatic.databinding.ActivityDataKasirBinding
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataKasirActivity : AppCompatActivity(), GetKasir {
    private lateinit var binding: ActivityDataKasirBinding
    private lateinit var datakasirAdapter: DataKasirAdapter
    private lateinit var dataKasirPresenter: DataKasirPresenter
    private var kasirResponseItem = mutableListOf<KasirResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDataKasirBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

        binding.btnTambahKasir.setOnClickListener {
            startActivity(Intent(this, TambahKasirActivity::class.java))
        }

        dataKasirPresenter = DataKasirPresenter()
        dataKasirPresenter.getKasir(this)
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
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val drawer_layout: DrawerLayout = findViewById(R.id.drawer_layout)

        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        } else{
            super.onBackPressed()
        }
    }

    override fun onSuccessGetBarang(kasirResponse: KasirResponse) {
        datakasirAdapter = DataKasirAdapter()
        kasirResponseItem = kasirResponse.data as MutableList<KasirResponseItem>
        var rv = binding.RecDataKasir
        rv.adapter = datakasirAdapter
        rv.layoutManager = LinearLayoutManager(this)
        datakasirAdapter.setDataList(kasirResponseItem)


    }

    override fun onFailedGetBarang(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}