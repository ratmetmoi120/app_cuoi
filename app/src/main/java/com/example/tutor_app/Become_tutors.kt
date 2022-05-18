package com.example.tutor_app

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tutor_app.config.RealPathUtil
import com.example.tutor_app.config.hostApi
import kotlinx.android.synthetic.main.activity_become_tutors.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.io.InputStream


class Become_tutors : AppCompatActivity() {

    private val REQUEST_IMAGE_GALLERY=132
    var getRealPath : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_become_tutors)

        displaypicture.setOnClickListener {
            val builder=AlertDialog.Builder(this)
            builder.setTitle("Upload hình ảnh")
            builder.setMessage("Lựa chọn của bạn là ?")
            builder.setPositiveButton("Album") { dialogInterface, i ->
                dialogInterface.dismiss()

                val intent=Intent(Intent.ACTION_PICK)
               intent.type="image/*"
                startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
            }
            builder.setNegativeButton("Camera"){ dialogInterface, i ->
                dialogInterface.dismiss()
            }
            val dialog:AlertDialog=builder.create()
            dialog.show()
        }
        var button: Button = findViewById(R.id.btn_login)
        button.setOnClickListener {
            uploadimage(getRealPath!!)
            dangkiTutor()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_GALLERY && resultCode==Activity.RESULT_OK && data != null) {
            displaypicture.setImageURI(data.data)
            getRealPath = RealPathUtil()?.getRealPath(this,data.data)!!

        } else {
            Toast.makeText(this, "Wronggg", Toast.LENGTH_SHORT).show()
        }
    }

    fun dangkiTutor(){
        var name : EditText = findViewById(R.id.username7)
        var mail : EditText = findViewById(R.id.username6)
        var trinhdo : EditText = findViewById(R.id.username5)
        var mon : EditText = findViewById(R.id.username4)
        var gioithieu : EditText = findViewById(R.id.username3)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(hostApi.REGIURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api: RegisterInterface = retrofit.create(RegisterInterface::class.java)
        val call: Call<String?>? = api.dangki(name.text.toString(),mail.text.toString(),trinhdo.text.toString(),mon.text.toString(),gioithieu.text.toString(),getRealPath!!.split("/").last()!!)
        call?.enqueue(object  : Callback<String?>{
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.e("success",response.body()!!)
                Log.e("check", name.toString())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e("fail",t.toString())
            }

        })
    }

    fun uploadimage(data : String){

        val file = File(data)
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        var part : MultipartBody.Part = MultipartBody.Part.createFormData("fileToUpload", file.getName(), reqFile)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(hostApi.REGIURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api: Logininterfacee = retrofit.create(Logininterfacee::class.java)
        val call: Call<String> = api.uploadImage(part)

        call?.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.e("Responsestring", response.body().toString())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e("error", t.toString())

            }
        })
    }
}