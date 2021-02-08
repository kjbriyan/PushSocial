package com.kjbriyan.socialapps.model

data class ResponsePosting(
	val data: List<DataItems>? = null,
	val status: Int? = null
)

data class KetItem(
	val komen: String? = null,
	val idPosting: String? = null,
	val id: String? = null,
	val suka: Int? = null,
	val username: String? = null
)

data class DataItems(
	val keterangan: String? = null,
	val img: Any? = null,
	val nama: String? = null,
	val id: String? = null,
	val ket: List<KetItem>? = null,
	val jmlLike: String? = null,
	val jmlCmn: String? = null
)

