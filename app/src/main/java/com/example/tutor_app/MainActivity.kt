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
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private var etUname: EditText? = null
    private  var etPass:EditText? = null
    private var btnlogin: Button? = null
    private var tvreg: TextView? = null
    private var preferenceHelper: PreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferenceHelper = PreferenceHelper(Res())
        etUname = findViewById<View>(R.id.username) as EditText
        etPass = findViewById<View>(R.id.pass) as EditText
        btnlogin = findViewById<View>(R.id.btn_login) as Button
        tvreg = findViewById<View>(R.id.textReg) as TextView
        tvreg!!.setOnClickListener {
            val intent = Intent(this@MainActivity, Res::class.java)
            startActivity(intent)
            this@MainActivity.finish()
        }
        btnlogin!!.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        val username = etUname!!.text.toString().trim { it <= ' ' }
        val password: String = etPass!!.getText().toString().trim({ it <= ' ' })
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(hostApi.REGIURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api: Logininterfacee = retrofit.create(Logininterfacee::class.java)
        val call: Call<String?>? = api.getUserLogin(username, password)
        call?.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.i("Responsestring", response.body().toString())
                //Toast.makeText()
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString())
                        val jsonresponse = response.body().toString()
                        parseLoginData(jsonresponse)
                    } else {
                        Log.i(
                            "onEmptyResponse",
                            "Returned empty response"
                        ) //Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }

    private fun parseLoginData(response: String) {
        try {
            val jsonObject = JSONObject(response)
            if (jsonObject.getString("status") == "true") {
                saveInfo(response)
                Toast.makeText(this@MainActivity, "Login Successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, Home::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
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