package com.kjbriyan.socialapps.ui.fragment.acount

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.ui.login.LoginActivity
import com.kjbriyan.socialapps.util.Helper
import com.kjbriyan.socialapps.util.SharedPrefs
import com.pixplicity.easyprefs.library.Prefs
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_acount.*
import kotlinx.android.synthetic.main.fragment_acount.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AcountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AcountFragment : Fragment(),AcountView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_acount, container, false)

        val id = Prefs.getString(SharedPrefs.idUser, "").toString()
        val name = v.findViewById<EditText>(R.id.et_name)?.text
        val pas = v.findViewById<EditText>(R.id.et_pass)?.text
        val btn = v.findViewById<Button>(R.id.btn_kirim)
        val presenter = AcountPresenter(this)

        v.btn_kirim.setOnClickListener {
            presenter.update(id,name.toString(),pas.toString())
            Log.d("sianu",""+pas.toString())
        }
        v.tv_logout.setOnClickListener {
            logout()
        }

        return v
    }

    companion object {
        fun newIntance(): AcountFragment {
            return AcountFragment()
        }
    }
    fun logout(){
//        activity.let {
            val dialogitem =
                    arrayOf<CharSequence>("Yes", "No")
            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Logout ?")
            builder.setItems(dialogitem, DialogInterface.OnClickListener { dialogInterface, i ->
                when (i) {
                    0 -> actlogout()
                    1 -> Toasty.normal(requireActivity(), "Cancel", Toast.LENGTH_SHORT).show()
                }
            })
            builder.create().show()
//        }
    }

    fun actlogout(){
        activity.let {
            Prefs.clear()
            Helper().moveActivity(it, LoginActivity::class.java)
            activity?.finish()
        }
    }

    override fun onLoading() {
        view?.pb_acc?.visibility = View.VISIBLE
    }

    override fun onHideloading() {
        view?.pb_acc?.visibility = View.GONE
    }

    override fun onSendSukses(t: ResponseStatus?) {
        Toasty.success(requireContext(),t?.messages.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDataeror(t: Throwable) {
        Toasty.success(requireContext(),t?.cause.toString(), Toast.LENGTH_SHORT).show()
    }
}