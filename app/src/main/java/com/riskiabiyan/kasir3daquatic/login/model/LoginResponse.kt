package com.riskiabiyan.kasir3daquatic.login.model

data class LoginResponse(
    val data: List<LoginResponseItem>
)

data class LoginResponseItem(
    val nama_user : String? = null,
    val role : String? = null,
    val email : String? = null,
    val password : String? = null,
    val no_hp : String? = null
)