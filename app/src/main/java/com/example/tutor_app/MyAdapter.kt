package com.example.tutor_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutor_app.config.hostApi
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(private val newsList: ArrayList<ListTuTor>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_layout,
            parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= newsList[position]
        var img : String = hostApi.REGIURL+"uploads/"+currentItem.getImg()
        Log.e("check img",img)
        Picasso.get().load(img).into(holder.titleImage)
        holder.ten.text=currentItem.getName()!!
        holder.gt.text=currentItem.getGender()
        holder.mon.text=currentItem.getObject()
        holder.trdo.text=currentItem.getTrdo()
        holder.gth.text=currentItem.getIntro()
        holder.id.text= currentItem.getId().toString()

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val titleImage:ShapeableImageView=itemView.findViewById(R.id.anh)
        val ten :TextView=itemView.findViewById(R.id.ten)
        val gt :TextView=itemView.findViewById(R.id.gtinh)
        val mon :TextView=itemView.findViewById(R.id.mon)
        val trdo :TextView=itemView.findViewById(R.id.trinhdo)
        val gth :TextView=itemView.findViewById(R.id.gth)
        val id :TextView=itemView.findViewById(R.id.id)

    }
}

