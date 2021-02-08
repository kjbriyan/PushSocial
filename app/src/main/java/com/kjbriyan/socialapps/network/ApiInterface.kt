package com.kjbriyan.socialapps

import com.kjbriyan.socialapps.model.ResponseDashboard
import com.kjbriyan.socialapps.model.ResponseLiked
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
            @Field("username") email: String,
            @Field("password") password: String
    ): Call<ResponseStatus>

    @GET("post/indexx")
    fun getPost(): Call<ResponseDashboard>

    @POST("post/index/{id}")
    fun getkomen(
            @Path("id") id : String
    ): Call<ResponsePosting>

    @POST("liked/btnlike/{id}")
    fun getbtnlike(
        @Path("id") username : String
    ): Call<ResponseLiked>

    @POST("liked/like/{id}")
    fun postlike(
        @Path("id") username : String,
        @Field("liked") liked: String,
        @Field("id_post") idpost: String,

    ): Call<ResponseStatus>


    @FormUrlEncoded
    @POST("post/create")
    fun insertpost(
        @Field("nama") nama: String,
        @Field("keterangan") keterangan: String,
        @Field("img") img: String?,
        @Field("id_user")iduser: String?
    ): Call<ResponseStatus>

    @FormUrlEncoded
    @POST("oke/postlike")
    fun insertkomen(
        @Field("suka") suka: String,
        @Field("komen") komen: String,
        @Field("id_posting") id_post: String,
        @Field("username") username: String?
    ): Call<ResponseStatus>

    @FormUrlEncoded
    @POST("oke/postlike")
    fun liked(
        @Field("suka") suka: String,
        @Field("komen") komen: String,
        @Field("id_posting") id_post: String,
        @Field("username") username: String?
    ): Call<ResponseStatus>


}