package com.kjbriyan.socialapps.ui.fragment.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.adapter.RvAdapterEdit
import com.kjbriyan.socialapps.adapter.RvAdapterPost
import com.kjbriyan.socialapps.model.DataItemss
import com.kjbriyan.socialapps.ui.fragment.dashboard.DashboardPresenter
import com.kjbriyan.socialapps.util.SharedPrefs
import com.pixplicity.easyprefs.library.Prefs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment(), EditView {
    // TODO: Rename and change types of parameters
    lateinit var recyclerView: RecyclerView
    lateinit var adapterr: RvAdapterEdit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        recyclerView = view.findViewById(R.id.rv_edit)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val id = Prefs.getString(SharedPrefs.idUser, "").toString()
        val presenter = EditPresenter(this)
        activity.let {
            presenter.getdata(id)
        }

        super.onActivityCreated(savedInstanceState)
        return view
    }

    companion object {
        fun newIntance(): EditFragment {
            return EditFragment()
        }
    }

    override fun onShowLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_dash)?.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_dash)?.visibility = View.INVISIBLE
    }

    override fun onDataloaded(results: List<DataItemss>?) {
        if (results != null) {
            adapterr = RvAdapterEdit(results)
            activity.let {
                with(recyclerView) {
                    adapter = adapterr

                }
            }

        } else {
            Log.d("EditFragment", "null data")
        }
    }

    override fun onDataeror(message: Throwable) {
        Toast.makeText(activity, message.cause.toString(), Toast.LENGTH_SHORT).show()
    }
    override fun onStart() {
        super.onStart()
        val presenter = EditPresenter(this)
        val id = Prefs.getString(SharedPrefs.idUser, "").toString()
        activity.let {
            presenter.getdata(id)

        }
    }
}