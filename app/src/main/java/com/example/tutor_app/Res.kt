package com.example.tutor_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tutor_app.config.hostApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_res.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class Res : AppCompatActivity() {
    private var etusername:EditText? = null
    private var etmail: EditText? = null
    private var etsdt:EditText? = null
    private var etpassword:EditText? = null
    private var btnregister: Button? = null
    private var tvlogin: TextView? = null
    private var preferenceHelper: PreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res)
        preferenceHelper = PreferenceHelper(this)
//        if (preferenceHelper!!.getIsLogin()) {
//            val intent = Intent(this@Res, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//            finish()
//        }
        etusername = findViewById<View>(R.id.username) as EditText
        etmail = findViewById<View>(R.id.mail) as EditText
        etsdt = findViewById<View>(R.id.sdt) as EditText
        etpassword = findViewById<View>(R.id.pass) as EditText
        btnregister = findViewById<View>(R.id.button) as Button
        tvlogin = findViewById<View>(R.id.textlogin) as TextView
        tvlogin!!.setOnClickListener {
            val intent = Intent(this@Res, MainActivity::class.java)
            startActivity(intent)
            this@Res.finish()
        }
        btnregister!!.setOnClickListener { registerMe() }
    }

    private fun registerMe() {
        val username: String = etusername!!.getText().toString()
        val mail: String = etmail!!.getText().toString()
        val sdt: String = etmail!!.getText().toString()
        val password: String = etpassword!!.getText().toString()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(hostApi.REGIURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api = retrofit.create(RegisterInterface::class.java)
        val call: Call<String?>? = api.getUserRegiter(username,mail,sdt,password)
        call?.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.i("Responsestring", response.body().toString())
                //Toast.makeText()
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Log.i("onSuccess Register", response.body().toString())
                        val jsonresponse = response.body().toString()
                        try {
                            parseRegData(jsonresponse)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    } else {
                        Log.i(
                            "onEmptyResponseRegister",
                            "Returned empty response"
                        ) //Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }

    @Throws(JSONException::class)
    private fun parseRegData(response: String) {
        val jsonObject = JSONObject(response)
        if (jsonObject.optString("status") == "true") {
            saveInfo(response)
            Toast.makeText(this@Res, "Registered Successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Res, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this@Res, jsonObject.getString("message"), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun saveInfo(response: String) {
        preferenceHelper!!.putIsLogin(true)
        try {
            val jsonObject = JSONObject(response)
            if (jsonObject.getString("status") == "true") {
                val dataArray = jsonObject.getJSONArray("data")
                for (i in 0 until dataArray.length()) {
                    val dataobj = dataArray.getJSONObject(i)
                    preferenceHelper!!.putName(dataobj.getString("name"))
                    preferenceHelper!!.putHobby(dataobj.getString("hobby"))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}