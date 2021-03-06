package com.kjbriyan.socialapps.ui.registrasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.ResponseStatus
import kotlinx.android.synthetic.main.activity_regis.*

class RegisActivity : AppCompatActivity(), RegisView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)
        val presenter = RegisPresenter(this)
        btn_regis.setOnClickListener {
            Log.d("regiss",et_pass.text.toString())
            presenter.regis(et_uname.text.toString(),et_pass.text.toString(),et_nama.text.toString())
        }
    }


    override fun onLoading() {
        pb_regis.visibility = View.VISIBLE
    }

    override fun onHideloading() {
        pb_regis.visibility = View.GONE
    }

    override fun onDatasukses(t: ResponseStatus?) {
        Toast.makeText(this,t?.messages?.success.toString(),Toast.LENGTH_SHORT).show()
        if (t?.messages?.success.toString().equals("Data Saved")){
            finish()
        }
    }

    override fun onDataeror(t: Throwable) {
        Toast.makeText(this,t.message.toString(),Toast.LENGTH_SHORT).show()
    }
}