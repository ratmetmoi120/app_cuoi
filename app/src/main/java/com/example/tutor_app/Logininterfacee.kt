package com.example.tutor_app

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Logininterfacee {
    @FormUrlEncoded
    @POST("login.php")
    fun getUserLogin(
        @Field("taikhoan") username: String?,
        @Field("matkhau") password: String?
    ): Call<String?>?

    @Multipart
    @POST("uploadImage.php")
    fun uploadImage(@Part fileToUpload : MultipartBody.Part):Call<String>

}