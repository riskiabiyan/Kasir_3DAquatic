package com.riskiabiyan.kasir3daquatic.kategori.model

data class KategoriResponse(
	val data: List<KategoriResponseItem?>? = null
)

data class KategoriResponseItem(
	val updated_at: String? = null,
	val created_at: String? = null,
	val id_kategori: Int? = null,
	val nama_kategori: String? = null
)

