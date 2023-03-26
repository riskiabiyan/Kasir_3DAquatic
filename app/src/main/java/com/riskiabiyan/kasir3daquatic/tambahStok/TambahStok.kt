package com.riskiabiyan.kasir3daquatic.tambahStok

import android.content.Context
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
import com.riskiabiyan.kasir3daquatic.databinding.ActivityTambahBarangBinding
import com.riskiabiyan.kasir3daquatic.databinding.ActivityTambahStokBinding
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import com.riskiabiyan.kasir3daquatic.tambahStok.model.TambahStokResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahStok : AppCompatActivity() {

    private lateinit var binding: ActivityTambahStokBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTambahStokBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val nama = intent.getStringExtra("nama").toString()
        val stok = intent.getStringExtra("stok").toString()




        init()

        binding.tvStokSKRG.text = stok
        binding.tvStokNama.text = nama
        binding.btnStokTambah.setOnClickListener {
            if(binding.tvStok.text.toString().isEmpty()){
                Toast.makeText(this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()

                }else{
            update_stok()
        }


        }

    }

    private fun update_stok(){
        val id = intent.getStringExtra("id")?.toInt()
        val edStok = binding.tvStok.text.toString().toInt()

        RetrofitClient.instance.update_stok(
            id!!, edStok
        ).enqueue(object : Callback<TambahStokResponse>{
            override fun onResponse(
                call: Call<TambahStokResponse>,
                response: Response<TambahStokResponse>
            ) {
               Toast.makeText(this@TambahStok, "success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@TambahStok, BarangMasuk::class.java))
            }

            override fun onFailure(call: Call<TambahStokResponse>, t: Throwable) {
                Toast.makeText(this@TambahStok, "Data gagal ditambahkan"+t.message, Toast.LENGTH_SHORT).show()
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