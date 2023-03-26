package com.riskiabiyan.kasir3daquatic.barangMasuk

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
import com.riskiabiyan.kasir3daquatic.barang.BarangActivity
import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponse
import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponseItem
import com.riskiabiyan.kasir3daquatic.barang.presenter.BarangPresenter
import com.riskiabiyan.kasir3daquatic.barang.view.GetBarang
import com.riskiabiyan.kasir3daquatic.barangMasuk.adapter.BarangMasukAdapter
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.databinding.ActivityBarangMasukBinding
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity

class BarangMasuk : AppCompatActivity(), GetBarang {
    private lateinit var binding : ActivityBarangMasukBinding
    private lateinit var barangMasukAdapter: BarangMasukAdapter
    private lateinit var barangPresenter: BarangPresenter
    private var barangResponseItem = mutableListOf<BarangResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBarangMasukBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

        barangPresenter = BarangPresenter()
        barangPresenter.getBarang(this)

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

    override fun onSuccessGetBarang(barangResponse: BarangResponse) {
        barangMasukAdapter = BarangMasukAdapter(this)
        barangResponseItem = barangResponse.data as MutableList<BarangResponseItem>
        var rv = binding.RecBarangMasuk
        rv.adapter = barangMasukAdapter
        rv.layoutManager = LinearLayoutManager(this)
        barangMasukAdapter.setDataList(barangResponseItem)
    }

    override fun onFailedGetBarang(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}