package com.example.tutor_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.tutor_app.config.hostApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.math.log

class Parentt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parentt)
        var button: Button = findViewById(R.id.btn_login)
        button.setOnClickListener {
            dangkigiasu()
        }
    }
        fun dangkigiasu() {
            var id:EditText =findViewById(R.id.id)
            var name: EditText = findViewById(R.id.nameParent)
            var diachi : EditText = findViewById(R.id.addressParent)
            var sdt : EditText = findViewById(R.id.phoneParent)
            var lop : EditText = findViewById(R.id.classParent)
            var lich : EditText = findViewById(R.id.lichhocParent)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(hostApi.REGIURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
            val api: RegisterInterface = retrofit.create(RegisterInterface::class.java)
            val call: Call<String?>? = api.dangkigiasu(name.text.toString(),diachi.text.toString(),sdt.text.toString(),lop.text.toString(),lich.text.toString(), id.text.toString())
            call?.enqueue(object  : Callback<String?>{
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    Log.e("success",response.body().toString())
                    Log.e("check",id.toString())
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    Log.e("fail",t.toString())
                }

            })
    }

}