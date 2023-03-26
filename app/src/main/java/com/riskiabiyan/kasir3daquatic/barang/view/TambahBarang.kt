package com.riskiabiyan.kasir3daquatic.barang.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.barang.BarangActivity
import com.riskiabiyan.kasir3daquatic.barang.model.BarangResponsePost
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahBarang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_barang)
        val msg = intent.getStringExtra("msg").toString()

        Toast.makeText(this, msg , Toast.LENGTH_LONG).show()
        init()



        val btnTambahBrg : Button = findViewById(R.id.btnTambahBarang)
        btnTambahBrg.setOnClickListener(View.OnClickListener {
            barangAdd()
        })
    }

    private fun barangAdd() {
        val msg = intent.getStringExtra("msg")
        val msgiNT : Int = msg.toString().toInt()

        val edketerangan : EditText = findViewById(R.id.edTketerangan)
        val edTKString : String = edketerangan.text.toString()

        val edNamaBrg : EditText = findViewById(R.id.edTNamaBarang)
        val edNamaBrgString : String = edNamaBrg.text.toString()

        val edHarga_jual : EditText = findViewById(R.id.edThargaJual)
        var edHarga_jualDouble : Int = edHarga_jual.text.toString().toInt()

        val edHargaAwal : EditText = findViewById(R.id.edThargamodal)
        var edHargaAwalDouble : Int = edHargaAwal.text.toString().toInt()

        val edStok : EditText = findViewById(R.id.edTStok)
        var edStokInt : Int = edStok.text.toString().toInt()

        RetrofitClient.instance.createBarang(
            msgiNT,
            edNamaBrgString,
            edTKString,
            edHargaAwalDouble,
            edHarga_jualDouble,
            edStokInt

        ).enqueue(object : Callback<BarangResponsePost>{
            override fun onResponse(
                call: Call<BarangResponsePost>,
                response: Response<BarangResponsePost>
            ) {
                val responText = "Berhasil Ditambahkan"
                Toast.makeText(this@TambahBarang, responText, Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<BarangResponsePost>, t: Throwable) {
                Toast.makeText(this@TambahBarang, t.message, Toast.LENGTH_LONG).show()
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