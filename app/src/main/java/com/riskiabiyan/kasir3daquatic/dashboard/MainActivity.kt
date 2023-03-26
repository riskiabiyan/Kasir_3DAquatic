package com.riskiabiyan.kasir3daquatic.dashboard


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.barang.BarangActivity
import com.riskiabiyan.kasir3daquatic.barangMasuk.BarangMasuk
import com.riskiabiyan.kasir3daquatic.dataKasir.DataKasirActivity
import com.riskiabiyan.kasir3daquatic.kasir.KasirActivity
import com.riskiabiyan.kasir3daquatic.kategori.KategoriActivity
import com.riskiabiyan.kasir3daquatic.laporan.LaporanActivity
import com.riskiabiyan.kasir3daquatic.login.LoginActivity
import com.riskiabiyan.kasir3daquatic.preferenceManager.PreffManager


class MainActivity : AppCompatActivity() {

    private lateinit var preffManager: PreffManager
    private lateinit var username: String
    private lateinit var tvResponS: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val role = intent.getStringExtra("role")


        preffManager = PreffManager(this)
        username = preffManager.getUsername().toString()
        tvResponS = findViewById(R.id.tvRespon)

        init()
        checkLogin()

        val btnLogout: Button = findViewById(R.id.btnLogoutt)
        btnLogout.setOnClickListener {
            logout()
        }
        val btnLap: Button = findViewById(R.id.btnLaporan)
        btnLap.setOnClickListener {
//            val role = intent.getStringExtra("role").toString()
//            if(role == "admin"){
//                startActivity(Intent(this, LaporanActivity::class.java))
//            }else{
//                Toast.makeText(this, "Menu khusus admin", Toast.LENGTH_LONG).show()
//            }
            if (username == "admin") {
                startActivity(Intent(this, LaporanActivity::class.java))
            } else {
                Toast.makeText(this, "Menu Khusus Admin", Toast.LENGTH_SHORT).show()
            }
        }

        val btnKasir: Button = findViewById(R.id.btnDashboardKasir)
        btnKasir.setOnClickListener {
//            val role = intent.getStringExtra("role").toString()
//            if(role == "admin"){
//                startActivity(Intent(this, DataKasirActivity::class.java))
//            }else{
//                Toast.makeText(this, "Menu khusus admin", Toast.LENGTH_LONG).show()
//            }
//        }
            if (username == "admin") {
                startActivity(Intent(this, DataKasirActivity::class.java))
            } else {
                Toast.makeText(this, "Menu Khusus Admin", Toast.LENGTH_SHORT).show()
            }

        }
    }
    fun checkLogin() {
        if (preffManager.isLogin() == false) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun logout() {
        preffManager.removeData()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onBackPressed() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val drawer_layout: DrawerLayout = findViewById(R.id.drawer_layout)

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun init() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val drawer_layout: DrawerLayout = findViewById(R.id.drawer_layout)

        setSupportActionBar(toolbar)
        val navView: NavigationView = findViewById(R.id.nav_view)

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_barang -> {
                    startActivity(Intent(this, BarangActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_barangmasuk -> {
                    startActivity(Intent(this, BarangMasuk::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_kasir -> {
                    startActivity(Intent(this, KasirActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_kategori -> {
                    startActivity(Intent(this, KategoriActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }

        // animasi
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }
}


