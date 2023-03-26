package com.riskiabiyan.kasir3daquatic.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.riskiabiyan.kasir3daquatic.R
import com.riskiabiyan.kasir3daquatic.api.RetrofitClient
import com.riskiabiyan.kasir3daquatic.dashboard.MainActivity
import com.riskiabiyan.kasir3daquatic.databinding.ActivityLoginBinding
import com.riskiabiyan.kasir3daquatic.login.model.LoginResponse
import com.riskiabiyan.kasir3daquatic.loginKasir.LoginKasir
import com.riskiabiyan.kasir3daquatic.preferenceManager.PreffManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var preffManager: PreffManager


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnLoginKasir.setOnClickListener {
            startActivity(Intent(this, LoginKasir::class.java))
        }


        preffManager = PreffManager(this)
        binding.btnLogin.setOnClickListener {

            val email = binding.edEmail.text.toString()
            val pw = binding.edPassword.text.toString()

            when{
                email.isEmpty() ->{
                    Toast.makeText(this, "Email Kosong", Toast.LENGTH_LONG).show()
                }
                pw.isEmpty() ->{
                    Toast.makeText(this, "Password Kosong", Toast.LENGTH_LONG).show()
                }
                else->{
                    ceklogin()
                }
            }
        }
    }

    private fun ceklogin(){
        val email = binding.edEmail.text.toString()
        val pw = binding.edPassword.text.toString()
        val role = binding.role.text.toString()

        RetrofitClient.instance.login(
            role,email,pw
        ).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val ress = response.code().toString()

                if(ress == "200"){
                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_LONG).show()
                    preffManager.setLogin(true)
                    preffManager.setUsername(role)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LoginActivity, "Data pengguna tidak ditemukan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Kesahan, periksa koneksi anda", Toast.LENGTH_LONG).show()
            }

        })
    }
}