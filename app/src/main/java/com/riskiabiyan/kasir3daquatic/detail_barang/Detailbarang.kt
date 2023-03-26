package com.riskiabiyan.kasir3daquatic.detail_barang

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
import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponse
import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponseItem
import com.riskiabiyan.kasir3daquatic.barangMasuk.BarangMasuk
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.databinding.ActivityDetailbarangBinding
import com.riskiabiyan.kasir3daquatic.detail_barang.adapter.DetailBarangAdapter
import com.riskiabiyan.kasir3daquatic.detail_barang.model.DetailBarangResponse
import com.riskiabiyan.kasir3daquatic.detail_barang.model.DetailbarangItem
import com.riskiabiyan.kasir3daquatic.detail_barang.view.GetDetailBarang
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detailbarang : AppCompatActivity(), GetDetailBarang {
    private lateinit var binding: ActivityDetailbarangBinding
    private lateinit var detailBarangAdapter: DetailBarangAdapter
    private var detailbarangItem = mutableListOf<DetailbarangItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailbarangBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val msg = intent.getStringExtra("msg")

        Toast.makeText(this@Detailbarang, msg.toString(), Toast.LENGTH_SHORT).show()


        tampilkan(this)

//        binding.btnDelBRG.setOnClickListener {
////            deletebarang()
//        }

        init()
    }

    private fun tampilkan(getDetailBarang: GetDetailBarang) {
        val msg = intent.getStringExtra("msg")

        RetrofitClient.instance.getDetilBarang(

            msg.toString().toInt()

        ).enqueue(object : Callback<DetailBarangResponse>{
            override fun onResponse(
                call: Call<DetailBarangResponse>,
                response: Response<DetailBarangResponse>
            ) {
                response.body()?.let { getDetailBarang.onSuccessGetBarang(it) }
            }

            override fun onFailure(call: Call<DetailBarangResponse>, t: Throwable) {
                getDetailBarang.onFailedGetBarang(t.localizedMessage)
            }

        })
    }

    private fun deletebarang(){
        val msg = intent.getStringExtra("msg")?.toInt()
        msg?.let {
            RetrofitClient.instance.deleteBarang(
                it
            ).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(this@Detailbarang, response.code(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Detailbarang, BarangActivity::class.java))
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@Detailbarang, t.message, Toast.LENGTH_SHORT).show()
                }

            })
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

    override fun onSuccessGetBarang(detailBarangResponse: DetailBarangResponse) {
        detailBarangAdapter = DetailBarangAdapter()
        detailbarangItem = detailBarangResponse.data as MutableList<DetailbarangItem>
        var rv = binding.RecDetilBarang
        rv.adapter = detailBarangAdapter
        rv.layoutManager = LinearLayoutManager(this)
        detailBarangAdapter.setDataList(detailbarangItem)

    }

    override fun onFailedGetBarang(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}