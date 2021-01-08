package com.kjbriyan.socialapps.ui.detailcontent


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.adapter.RvAdapterKomen
import com.kjbriyan.socialapps.model.DataItems
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
//        val inflater: LayoutInflater = ap.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        btn_cht.setOnClickListener {
//            val btn = BottomSheetDialog(this@DetailActivity, R.style.BottomSheatTheme)
//            val v = LayoutInflater.from(applicationContext)
//                    .inflate(R.layout.layout_slideup, findViewById(R.id.btmsheat) as LinearLayout?)
//
//            pb = v.findViewById<View>(R.id.pb_dash) as ProgressBar
//            btn.setContentView(v)
//            btn.show()
//        }

//        val komen = et_komen.text.toString()
        val presenter = DetailPresenter(this)
        val id = intent.extras?.getString("id").toString()
        val name = Prefs.getString(SharedPrefs.name, "").toString()

        btn_komen.setOnClickListener {
            val komen = findViewById<EditText>(R.id.et_komenn).text.toString()
            Log.d("sitom"," "+komen)
            presenter.postdata(id,komen,"",name)
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

            val adapaterr = RvAdapterKomen(t)
            Log.d("lia", "aa  "+t.get(0).username)
            val uname = t.get(0).username
//            if ()
            rv_komen.adapter = RvAdapterKomen(t)
        } else {
            Toasty.error(this, "null komentar", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSendSukses(t: ResponseStatus?) {
        Toasty.success(this, t?.status.toString(), Toast.LENGTH_SHORT).show()
        val presenter = DetailPresenter(this@DetailActivity)
        val id = intent.extras?.getString("id")
        presenter.getdata(id.toString())
    }

    override fun onDataeror(t: Throwable) {
        Toasty.error(this, t.cause.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        val presenter = DetailPresenter(this@DetailActivity)
        val id = intent.extras?.getString("id")
        presenter.getdata(id.toString())
    }
}