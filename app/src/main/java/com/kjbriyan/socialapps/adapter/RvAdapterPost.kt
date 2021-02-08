package com.kjbriyan.socialapps.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.model.DataItems
import com.kjbriyan.socialapps.model.DataItemss
import com.kjbriyan.socialapps.ui.detailcontent.DetailActivity
import com.kjbriyan.socialapps.util.Helper
import com.kjbriyan.socialapps.util.SharedPrefs
import com.pixplicity.easyprefs.library.Prefs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_post.view.*

class RvAdapterPost(var data: List<DataItemss?>) :
        RecyclerView.Adapter<RvAdapterPost.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapterPost.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_post, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: RvAdapterPost.MyHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: DataItemss?) {

            Picasso.get().load(Initretrofit().IMAGE+get?.img).into(itemView.iv_barang)
            itemView.tv_keterangan.text = get?.nama+" \n" +get?.keterangan
            itemView.tv_like.text = get?.jmlLike
            itemView.tv_coment.text = get?.jmlCmn

            val uname = Prefs.getString(SharedPrefs.username,"").toString()
//            for (i in 0 until btn!!.size){
//                if (btn[i]?.username? == Shared){
//                    itemView.star_button.visibility = View.VISIBLE
//                }
//            }
            itemView.ln_barang.setOnClickListener {
                val i = Intent(itemView.context,DetailActivity::class.java)
                i.putExtra("id",get?.id)
                i.putExtra("img",get?.img.toString())
//                i.putExtra("img",get?.img)
                i.putExtra("ket",get?.keterangan)
                itemView.context.startActivity(i)
            }
//            itemView.star_button.setOnClickListener {
//
//            }

        }
    }
}
