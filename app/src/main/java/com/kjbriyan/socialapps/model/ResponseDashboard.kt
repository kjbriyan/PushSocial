package com.kjbriyan.socialapps.model

import com.google.gson.annotations.SerializedName

data class ResponseDashboard(

	@field:SerializedName("data")
	val data: List<DataItemss>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItemss(

	@field:SerializedName("jml_like")
	val jmlLike: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("jml_cmn")
	val jmlCmn: String? = null
)
