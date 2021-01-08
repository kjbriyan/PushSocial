package com.kjbriyan.socialapps.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.model.DataItems
import com.kjbriyan.socialapps.model.KetItem
import kotlinx.android.synthetic.main.list_komen.view.*

class RvAdapterKomen(var data: List<KetItem?>) :
        RecyclerView.Adapter<RvAdapterKomen.MyHolder>() {
    override fun onBindViewHolder(holder: RvAdapterKomen.MyHolder, position: Int) {
        holder.bind(data?.get(position))

    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapterKomen.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_komen, parent, false)
        return MyHolder(v)
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: KetItem?) {
            itemView.tv_komen.text =get?.username+" : "+ get?.komen


        }
    }
}

