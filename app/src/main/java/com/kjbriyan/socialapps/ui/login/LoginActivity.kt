package com.kjbriyan.socialapps.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsitek.inventorymvp.ui.ui.login.LoginPresenter
import com.kjbriyan.socialapps.ui.registrasi.RegisActivity
import com.google.android.material.textfield.TextInputEditText
import com.kjbriyan.socialapps.DataItem
import com.kjbriyan.socialapps.ui.HomeActivity
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.util.Helper
import com.kjbriyan.socialapps.util.SharedPrefs
import com.pixplicity.easyprefs.library.Prefs
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {
    private val TAG = "Retrofit"
    private lateinit var pb: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val id = Prefs.getString(SharedPrefs.idUser, "").toString()
        if (id == "") {
            Log.d(TAG, "null ")
        } else {
            Helper().moveActivity(this, HomeActivity::class.java)
            Log.d(TAG, "id " + Prefs.getString(SharedPrefs.username, ""))
            finish()
        }
        pb = findViewById(R.id.pb_login)
        val presenter = LoginPresenter(this@LoginActivity)
        var uname = findViewById<TextInputEditText>(R.id.et_username).text
        var pass = findViewById<TextInputEditText>(R.id.et_pw).text
        btn_login.setOnClickListener {
            presenter.login(uname.toString(), pass.toString())
        }
        tv_signup.setOnClickListener {
            Helper().moveActivity(this, RegisActivity::class.java)
        }
    }

    override fun onShowLoading() {
        pb.visibility = View.VISIBLE
        Log.d(TAG, "show")
    }

    override fun onHideLoading() {
        pb.visibility = View.GONE
        Log.d(TAG, "hide")
    }

    override fun onDataloaded(results: List<DataItem?>) {
        Log.d(TAG, "data")

        if (results.isNotEmpty()) {
            Prefs.clear()
            Log.d(TAG, "data " + results.get(0)?.name)
            Prefs.putString(SharedPrefs.idUser, results.get(0)?.idUser)
            Prefs.putString(SharedPrefs.name, results.get(0)?.name)
            Prefs.putString(SharedPrefs.level, results.get(0)?.level)
            Prefs.putString(SharedPrefs.username, results.get(0)?.username)
            Helper().moveActivity(this@LoginActivity, HomeActivity::class.java)

            Toasty.success(this@LoginActivity, "Sukses", Toast.LENGTH_SHORT).show()
        } else {
            Toasty.error(this@LoginActivity, "user password salah", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDataeror(message: Throwable) {
        Log.d(TAG, "dateror")
        Toasty.error(this@LoginActivity, message.toString(), Toast.LENGTH_SHORT).show()
    }
}