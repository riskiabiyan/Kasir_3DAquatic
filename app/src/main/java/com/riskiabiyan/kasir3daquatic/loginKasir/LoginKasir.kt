package com.riskiabiyan.kasir3daquatic.loginKasir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.databinding.ActivityLoginKasirBinding
import com.riskiabiyan.kasir3daquatic.login.LoginActivity
import com.riskiabiyan.kasir3daquatic.login.model.LoginResponse
import com.riskiabiyan.kasir3daquatic.preferenceManager.PreffManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginKasir : AppCompatActivity() {
    private lateinit var binding: ActivityLoginKasirBinding
    private lateinit var preffManager: PreffManager


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginKasirBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preffManager = PreffManager(this)

        binding.btnLoginAdmin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnLoginK.setOnClickListener {
            val email = binding.edEmailK.text
            val pw = binding.edPasswordK.text

            when{
                email.isEmpty() ->{
                    Toast.makeText(this, "Email Kosong", Toast.LENGTH_LONG).show()
                }
                pw.isEmpty() ->{
                    Toast.makeText(this, "Password Kosong", Toast.LENGTH_LONG).show()
                }
                else->{
                    cekloginKasir()
                }
            }
        }


    }

    private fun cekloginKasir() {
        val email = binding.edEmailK.text.toString()
        val pw = binding.edPasswordK.text.toString()
        val role = binding.role.text.toString()

        RetrofitClient.instance.login(
            role,email,pw
        ).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val ress = response.code().toString()

                if(ress == "200"){
                    Toast.makeText(this@LoginKasir, ress, Toast.LENGTH_LONG).show()
                    preffManager.setLogin(true)
                    preffManager.setUsername(role)
                    val intent = Intent(this@LoginKasir, MainActivity::class.java)
                    intent.putExtra("role", role)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LoginKasir, "Data pengguna tidak ditemukan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginKasir, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}