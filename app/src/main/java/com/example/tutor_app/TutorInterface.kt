package com.example.tutor_app

import retrofit2.Call
import retrofit2.http.*

interface TutorInterface {

    @GET("giasu.php")
    fun getTuTor(@Query("name") name : String): Call<ArrayList<ListTuTor>>?
}