package com.riskiabiyan.kasir3daquatic.detail_kategori

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.barang.BarangActivity
import com.riskiabiyan.kasir3daquatic.barangMasuk.BarangMasuk
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailKategori : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kategori)

        val msg = intent.getStringExtra("msg")


        val btnHapus : Button = findViewById(R.id.btnYakinHapusKategori)
        btnHapus.setOnClickListener {
            HapusKategori()
        }

        val btnBatal : Button = findViewById(R.id.btnBatalHapusKategori)
        btnBatal.setOnClickListener {
            startActivity(Intent(this, KategoriActivity::class.java))
        }

        Toast.makeText(this, msg , Toast.LENGTH_LONG).show()

        init()


    }

    private fun HapusKategori() {
        val msg = intent.getStringExtra("msg")?.toInt()

        msg?.let {
            RetrofitClient.instance.deleteKategori(
                it
            ).enqueue(object :Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    Toast.makeText(this@DetailKategori, response.code().toString(), Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                    Toast.makeText(this@DetailKategori, t.message, Toast.LENGTH_LONG).show()
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
}