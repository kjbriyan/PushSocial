package com.kjbriyan.socialapps.ui.detailcontent


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.adapter.RvAdapterKomen
import com.kjbriyan.socialapps.model.DataIteem
import com.kjbriyan.socialapps.model.KetItem
import com.kjbriyan.socialapps.util.SharedPrefs
import com.pixplicity.easyprefs.library.Prefs
import com.squareup.picasso.Picasso
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_slideup.*
import kotlinx.android.synthetic.main.list_post.view.*


class DetailActivity : AppCompatActivity(), DetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val presenter = DetailPresenter(this)
        val id = intent.extras?.getString("id").toString()
        val jmllike = intent.extras?.getString("jml").toString()
        val name = Prefs.getString(SharedPrefs.name, "").toString()
        tv_jmllike.setText(jmllike)
        btn_komen.setOnClickListener {
            val komen = findViewById<EditText>(R.id.et_komenn).text.toString()
            Log.d("sitom", " " + komen)
            presenter.postdata(id, komen, "", name)
        }

        val ivnlike = findViewById<ImageView>(R.id.iv_nlikee)
        ivnlike.setOnClickListener {
            Log.d("sitom", " " + name + id)
            presenter.btnlike(name, "1", id)
            presenter.getlike(name,id)
        }
        iv_like.setOnClickListener {
            Log.d("sitom", " " + id)
            presenter.btnlike(name, "2", id)
            presenter.getlike(name,id)
        }
        Picasso.get().load(Initretrofit().IMAGE + intent.extras?.getString("img")).into(iv_post)
        tv_keterangan.text = intent.extras?.getString("ket")
    }


    override fun onLoading() {
        pb_dash.visibility = View.VISIBLE
    }

    override fun onHideloading() {
        pb_dash.visibility = View.INVISIBLE
    }

    override fun onDatasukses(t: List<KetItem>?) {

        if (t != null) {
            Log.d("lia", "aa  " + t.get(0).username)

            rv_komen.adapter = RvAdapterKomen(t)
        } else {
            Toasty.error(this, "null komentar", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSendSukses(t: ResponseStatus?) {
        val komen = findViewById<EditText>(R.id.et_komenn).text.toString()
        Toasty.success(this, t?.status.toString(), Toast.LENGTH_SHORT).show()
        val presenter = DetailPresenter(this@DetailActivity)
        komen.isEmpty()
        val id = intent.extras?.getString("id")
        presenter.getdata(id.toString())
    }

    override fun onLikeCon(t: List<DataIteem>?) {
//        Log.d("onlikecon", "aa"+t?.get(0)?.liked.toString())
        if (t?.size == null) {
            Log.d("onlikecon", "is null")
        }
        else{
            if (t!!.isEmpty()) {
                iv_nlikee.visibility = View.VISIBLE
                iv_like.visibility = View.GONE
            } else {
                Log.d("onlikecon", t?.get(0)?.liked.toString())
                if (t?.get(0)?.liked?.toInt() == 2) {
                    iv_nlikee.visibility = View.VISIBLE
                    iv_like.visibility = View.GONE
                } else {
                    iv_like.visibility = View.VISIBLE
                    iv_nlikee.visibility = View.GONE
                }
            }
        }
    }

    override fun onDataeror(t: Throwable) {
        Toasty.error(this, t.cause.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        val presenter = DetailPresenter(this@DetailActivity)
        val id = intent.extras?.getString("id")
        val name = Prefs.getString(SharedPrefs.name, "").toString()
        presenter.getdata(id.toString())
        Log.d("statuss", "a"+name.toString())
        presenter.getlike(name,id.toString())
    }
}