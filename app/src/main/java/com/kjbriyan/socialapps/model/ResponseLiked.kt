package com.kjbriyan.socialapps.model

import com.google.gson.annotations.SerializedName

data class ResponseLiked(

	@field:SerializedName("data")
	val data: List<DataIteem>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataIteem(

	@field:SerializedName("id_post")
	val idPost: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("liked")
	val liked: String? = null
)
