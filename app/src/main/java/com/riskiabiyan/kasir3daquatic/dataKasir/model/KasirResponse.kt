package com.riskiabiyan.kasir3daquatic.dataKasir.model

data class KasirResponse (
    val data: List <KasirResponseItem>
        )

data class KasirResponseItem(
    val nama_user: String? = null,
    val role: String? = null,
    val email: String? = null,
    val no_hp: String? = null,
    val password: String? = null

)