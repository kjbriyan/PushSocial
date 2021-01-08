package com.kjbriyan.socialapps

import com.kjbriyan.socialapps.model.ResponsePosting
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("authen/create/{id}")
    fun regis(
        @Path("id") usernameid : String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name : String
    ): Call<ResponseStatus>

    @FormUrlEncoded
    @POST("authen/auth")
    fun userLogin(
        @Field("uname") email: String,
        @Field("pass") password: String
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("authen/update/{id}")
    fun userUpdate(
            @Path("id") id: String,
            @Field("uname") email: String,
            @Field("pass") password: String
    ): Call<ResponseStatus>

    @GET("post/indexx")
    fun getPost(): Call<ResponsePosting>

    @POST("post/index/{id}")
    fun getLike(
            @Path("id") id : String
    ): Call<ResponsePosting>


    @FormUrlEncoded
    @POST("post/create")
    fun insertpost(
        @Field("nama") nama: String,
        @Field("keterangan") keterangan: String,
        @Field("img") img: String?
    ): Call<ResponseStatus>

    @FormUrlEncoded
    @POST("oke/postlike")
    fun insertkomen(
        @Field("suka") suka: String,
        @Field("komen") komen: String,
        @Field("id_posting") id_post: String,
        @Field("username") username: String?
    ): Call<ResponseStatus>


}