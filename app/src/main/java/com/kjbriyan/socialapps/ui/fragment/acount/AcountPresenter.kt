package com.kjbriyan.socialapps.ui.fragment.acount

import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.ui.registrasi.RegisView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AcountPresenter(private val mView : AcountView) {

    fun update(id : String,name : String,pass : String){
        mView.onLoading()
        Initretrofit().getInstance().userUpdate(id,name,pass)
                .enqueue(object : Callback<ResponseStatus> {
                    override fun onResponse(
                            call: Call<ResponseStatus>,
                            response: Response<ResponseStatus>
                    ) {
                        mView.onSendSukses(response.body())
                        mView.onHideloading()
                    }

                    override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                        mView.onDataeror(t)
                        mView.onHideloading()
                    }

                })
    }
}