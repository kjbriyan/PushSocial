package com.kjbriyan.socialapps.ui.registrasi
import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.ResponseStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisPresenter(private val mView : RegisView) {

    fun regis(uname : String,pass : String,name : String){
        mView.onLoading()
        Initretrofit().getInstance().regis(uname,uname,pass,name)
            .enqueue(object : Callback<ResponseStatus>{
                override fun onResponse(
                    call: Call<ResponseStatus>,
                    response: Response<ResponseStatus>
                ) {
                    mView.onDatasukses(response.body())
                    mView.onHideloading()
                }

                override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                    mView.onDataeror(t)
                    mView.onHideloading()
                }

            })
    }
}