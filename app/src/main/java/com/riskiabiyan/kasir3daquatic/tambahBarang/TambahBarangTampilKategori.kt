package com.riskiabiyan.kasir3daquatic.tambahBarang

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
import com.riskiabiyan.kasir3daquatic.barangMasuk.BarangMasuk
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.databinding.ActivityTambahBarangTampilKategoriBinding
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import com.riskiabiyan.kasir3daquatic.kategori.model.KategoriResponse
import com.riskiabiyan.kasir3daquatic.kategori.model.KategoriResponseItem
import com.riskiabiyan.kasir3daquatic.kategori.presenter.KategoriPresenter
import com.riskiabiyan.kasir3daquatic.kategori.view.GetKategori
import com.riskiabiyan.kasir3daquatic.tambahBarang.adapter.TambahBarangTKAdapter

class TambahBarangTampilKategori : AppCompatActivity(), GetKategori {

    private lateinit var binding : ActivityTambahBarangTampilKategoriBinding
    private lateinit var tambahBarangTKAdapter: TambahBarangTKAdapter
    private lateinit var kategoriPresenter: KategoriPresenter
    private var kategoriResponseItem = mutableListOf<KategoriResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTambahBarangTampilKategoriBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        kategoriPresenter = KategoriPresenter()
        kategoriPresenter.getKategori(this)

        init()
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

    override fun onSuccessGetKategori(kategoriResponse: KategoriResponse) {
        tambahBarangTKAdapter = TambahBarangTKAdapter(this)
        kategoriResponseItem = kategoriResponse.data as MutableList<KategoriResponseItem>
        var rv = binding.RecTambahBarang
        rv.adapter = tambahBarangTKAdapter
        rv.layoutManager = LinearLayoutManager(this)
        tambahBarangTKAdapter.setDataList(kategoriResponseItem)
    }

    override fun onFailedGetKategori(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}