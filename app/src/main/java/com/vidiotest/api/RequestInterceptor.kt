package com.vidiotest.api

import com.kresya.models.*
import com.kresya.models.request.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @FormUrlEncoded
    @POST("account/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseModel<LoginModel>>

    @FormUrlEncoded
    @POST("account/login_facebook")
    fun loginFacebook(
        @Field("type") type: String,
        @Field("access_token") token: String
    ): Call<ResponseModel<LoginModel>>

    @FormUrlEncoded
    @POST("account/login_google")
    fun loginGoogle(
        @Field("type") type: String,
        @Field("id_token") token: String
    ): Call<ResponseModel<LoginModel>>

    @GET("website/product_info")
    fun requestProduct(
        @Query("url") url: String
    ): Call<ResponseModel<ProductRequestModel>>
}