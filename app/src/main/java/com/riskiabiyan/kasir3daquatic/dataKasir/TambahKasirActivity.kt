package com.riskiabiyan.kasir3daquatic.dataKasir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.riskiabiyan.kasir3daquatic.dataKasir.model.KasirResponse
import com.riskiabiyan.kasir3daquatic.databinding.ActivityTambahKasirBinding
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahKasirActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTambahKasirBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTambahKasirBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        binding.btnTambahKasirTK.setOnClickListener {
            tambah()
        }
    }

    private fun tambah() {
        var nama = binding.ednamaUserTK.text.toString()
        var email = binding.edemailTK.text.toString()
        var noHP = binding.ednoHpTK.text.toString()
        var pass = binding.edpassTK.text.toString()

        RetrofitClient.instance.createKasir(
            nama,
            "kasir",
            email,
            noHP,
            pass
        ).enqueue(object : Callback<KasirResponse>{
            override fun onResponse(call: Call<KasirResponse>, response: Response<KasirResponse>) {
                Toast.makeText(this@TambahKasirActivity, response.code(), Toast.LENGTH_LONG).show()
                startActivity(Intent(this@TambahKasirActivity, DataKasirActivity::class.java))
            }

            override fun onFailure(call: Call<KasirResponse>, t: Throwable) {
                Toast.makeText(this@TambahKasirActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
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
}