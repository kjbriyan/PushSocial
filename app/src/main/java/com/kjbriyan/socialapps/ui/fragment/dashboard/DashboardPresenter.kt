package com.kjbriyan.socialapps.ui.fragment.dashboard

import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.model.ResponseDashboard
import com.kjbriyan.socialapps.model.ResponsePosting
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardPresenter(private val view: DashboardView) {

    fun getdata() {
        view.onShowLoading()
        Initretrofit().getInstance().getPost().enqueue(object : Callback<ResponseDashboard> {
            override fun onResponse(
                call: Call<ResponseDashboard>,
                response: Response<ResponseDashboard>
            ) {
                view.onDataloaded(response.body()?.data)
                view.onHideLoading()
            }

            override fun onFailure(call: Call<ResponseDashboard>, t: Throwable) {
                view.onHideLoading()
                view.onDataeror(t)
            }

        })
    }
}