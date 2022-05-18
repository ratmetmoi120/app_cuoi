package com.example.tutor_app

import retrofit2.Call
import retrofit2.http.*

interface RegisterInterface {

    @FormUrlEncoded
    @POST("signup.php")
    fun getUserRegiter(
        @Field("username") username: String?,
        @Field("mail") mail: String?,
        @Field("sdt") sdt: String?,
        @Field("password") password: String?
    ): Call<String?>?

    @GET("dangki.php")
    fun dangki(
        @Query("name") username: String?,
        @Query("mail") mail: String?,
        @Query("trinhdo") trinhdo: String?,
        @Query("monhoc") monhoc: String?,
        @Query("gioithieu") gioithieu: String?,
        @Query("anh") anh : String?
    ): Call<String?>?

    @GET("dangkigiasu.php")
    fun dangkigiasu(
        @Query("name") name: String?,
        @Query("diachi") diachi: String?,
        @Query("sdt") sdt: String?,
        @Query("lop") lop: String?,
        @Query("lich") lich: String?,
        @Query("id") id: String?
    ):Call<String?>?
}