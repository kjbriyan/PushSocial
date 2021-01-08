package com.kjbriyan.socialapps

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("messages")
	val messages: String? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)