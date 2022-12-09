package com.example.myapplication.recycleadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R

class ProfileAdapter(private val context: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var datas = mutableListOf<ProfileData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgProfile: ImageView = itemView.findViewById(R.id.img_rv_photo)
        private val imgProfile2: ImageView = itemView.findViewById(R.id.img_rv_photo2)
        private val imgProfile3: ImageView = itemView.findViewById(R.id.img_rv_photo3)
        private val imgProfile4: ImageView = itemView.findViewById(R.id.img_rv_photo4)

        fun bind(item: ProfileData) {
            Glide.with(itemView).load(item.img).into(imgProfile)
            Glide.with(itemView).load(item.img).into(imgProfile2)
            Glide.with(itemView).load(item.img).into(imgProfile3)
            Glide.with(itemView).load(item.img).into(imgProfile4)
        }
    }


}