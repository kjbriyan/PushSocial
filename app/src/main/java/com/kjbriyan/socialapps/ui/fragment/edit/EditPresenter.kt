package com.kjbriyan.socialapps.ui.fragment.edit

import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.model.ResponseDashboard
import com.kjbriyan.socialapps.ui.fragment.dashboard.DashboardView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPresenter(private val view: EditView) {

    fun getdata(id : String) {
        view.onShowLoading()
        Initretrofit().getInstance().getpostuser(id).enqueue(object : Callback<ResponseDashboard> {
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