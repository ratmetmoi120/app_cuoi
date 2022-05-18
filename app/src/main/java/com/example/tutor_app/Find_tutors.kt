package com.example.tutor_app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutor_app.config.hostApi
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Find_tutors : AppCompatActivity() {
    private var ten:TextView? = null
    private var gt:TextView? = null
    private var mon:TextView? = null
    private var trdo:TextView? = null
    private var gth:TextView? = null
    private var id:TextView? = null
    private var anh:ImageView?= null
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ListTuTor>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_tutors)

        newRecyclerView=findViewById(R.id.recycleView)
        newRecyclerView.layoutManager= LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        anh = newRecyclerView.findViewById(R.id.anh)
        ten = newRecyclerView.findViewById(R.id.ten)
        gt = newRecyclerView.findViewById(R.id.gtinh)
        mon = newRecyclerView.findViewById(R.id.mon)
        trdo = newRecyclerView.findViewById(R.id.trinhdo)
        gth = newRecyclerView.findViewById(R.id.gth)
        id = newRecyclerView.findViewById(R.id.id)

        imageId= arrayOf(
            R.id.anh
        )
        heading= arrayOf(
            "Thầy: "
        )
        newArrayList= arrayListOf<ListTuTor>()
        search()
    }

    fun search(){
        var search : EditText = findViewById(R.id.search)
        search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0 != null){
                    getUserdata(p0.toString())
                }else{
                    getUserdata("")
                }
                Log.e("text : " , p0.toString())
            }

        })
    }

    private fun getUserdata(name : String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(hostApi.REGIURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: TutorInterface = retrofit.create(TutorInterface::class.java)
        val call: Call<ArrayList<ListTuTor>>? = api.getTuTor(name)
        call?.enqueue(object : Callback<ArrayList<ListTuTor>> {
            override fun onResponse(call: Call<ArrayList<ListTuTor>>, response: Response<ArrayList<ListTuTor>>) {
//                    ten?.setText(modal.getName())
//                    mon?.setText(modal.getObject())
//                    gt?.setText(modal.getGender())
//                    trdo?.setText(modal.getTrdo())
//                    gth?.setText(modal.getIntro())
//                    Picasso.get().load(modal.getImg()).into(anh)

                newRecyclerView.adapter=MyAdapter(response.body()!!)

            }

            override fun onFailure(call: Call<ArrayList<ListTuTor>>, t: Throwable) {
               Log.e("fail",t.toString())
            }

        })
//        for(i in imageId.indices) {
//            val news = News(imageId[i], heading[i])
//            newArrayList.add(news)
//        }
        Log.e("check newArrayList",newArrayList.toString())


    }
}
