package com.mhl.mvp_firebase.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mhl.mvp_firebase.R
import com.mhl.mvp_firebase.dataclasses.Professions

class RecyclerAdapter(private val data : ArrayList<Professions>, private val context : Context) : RecyclerView.Adapter<RecyclerAdapter.VH>() {

    private lateinit var myListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClick(listener: onItemClickListener){
        myListener = listener
    }

    class VH(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var image : ImageView = itemView.findViewById(R.id.professionImage)
        var text : TextView = itemView.findViewById(R.id.professionText)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.recycler_one_item, parent, false), myListener)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load(data[position].image).into(holder.image)
        holder.text.text = data[position].name
    }

    override fun getItemCount(): Int {
        return data.size
    }

}