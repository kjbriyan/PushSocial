package com.kjbriyan.socialapps.ui.fragment.dashboard

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.adapter.RvAdapterPost
import com.kjbriyan.socialapps.model.DataItems
import com.kjbriyan.socialapps.model.DataItemss
import com.kjbriyan.socialapps.ui.additem.AddPostActivity
import com.kjbriyan.socialapps.util.Helper
import com.kjbriyan.socialapps.util.SharedPrefs
import com.like.LikeButton
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_dashboard.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment(),DashboardView {
    // TODO: Rename and change types of parameters
    lateinit var recyclerView: RecyclerView
    lateinit var adapterr: RvAdapterPost
    lateinit var  smileButton : LikeButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val btn_add = view.findViewById(R.id.btn_add) as FloatingActionButton
        btn_add.setOnClickListener {
            val i = Intent(context, AddPostActivity::class.java)
            context?.startActivity(i)
        }
        recyclerView = view.findViewById(R.id.rv_post)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val presenter = DashboardPresenter(this)
        activity.let {
            presenter.getdata()
        }

        val level = Prefs.getString(SharedPrefs.level, "")
        if (level == "2") {
            view.findViewById<FloatingActionButton>(R.id.btn_add).visibility = View.GONE
        }

        super.onActivityCreated(savedInstanceState)
        return view
    }



    companion object {
        fun newIntance(): DashboardFragment {
            return DashboardFragment()
        }
    }

    override fun onShowLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_dash)?.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_dash)?.visibility = View.GONE
    }

    override fun onDataloaded(results: List<DataItemss>?) {
        if (results!!.isNotEmpty()) {
            adapterr = RvAdapterPost(results)
            activity.let {
                with(recyclerView) {
                    adapter = adapterr

                }
            }

        } else {
            Log.d("DashboardFragment", "null data")
        }
    }

    override fun onDataeror(message: Throwable) {
        Toast.makeText(activity, message.cause.toString(), Toast.LENGTH_SHORT).show()
    }
    override fun onStart() {
        super.onStart()
        val presenter = DashboardPresenter(this)
        activity.let {
            presenter.getdata()

        }
    }
}