package com.kjbriyan.socialapps.ui.fragment.dashboard

import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.model.ResponsePosting
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardPresenter(private val view: DashboardView) {

    fun getdata() {
        view.onShowLoading()
        Initretrofit().getInstance().getPost().enqueue(object : Callback<ResponsePosting> {
            override fun onResponse(
                call: Call<ResponsePosting>,
                response: Response<ResponsePosting>
            ) {
                view.onDataloaded(response.body()?.data)
                view.onHideLoading()
            }

            override fun onFailure(call: Call<ResponsePosting>, t: Throwable) {
                view.onHideLoading()
                view.onDataeror(t)
            }

        })
    }
}