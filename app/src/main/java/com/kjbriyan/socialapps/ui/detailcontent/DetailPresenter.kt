package com.kjbriyan.socialapps.ui.detailcontent

import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.model.ResponsePosting
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailPresenter (private val mView : DetailView){

    fun getdata(id : String){
        mView.onLoading()
        Initretrofit().getInstance().getkomen(id)
                .enqueue(object : Callback<ResponsePosting> {
                    override fun onResponse(
                            call: Call<ResponsePosting>,
                            response: Response<ResponsePosting>
                    ) {
                        val res = response.body()
                        mView.onDatasukses(res?.data?.get(0)?.ket)
                        mView.onHideloading()
                    }

                    override fun onFailure(call: Call<ResponsePosting>, t: Throwable) {
                        mView.onDataeror(t)
                        mView.onHideloading()
                    }

                })

    }

    fun postdata(id : String,komen : String, suka : String, uname : String){
        mView.onLoading()
        Initretrofit().getInstance().insertkomen(suka,komen,id,uname)
            .enqueue(object : Callback<ResponseStatus> {
                override fun onResponse(
                    call: Call<ResponseStatus>,
                    response: Response<ResponseStatus>
                ) {
                    getdata(id)
                    val res = response.body()
                    mView.onSendSukses(res)
                    mView.onHideloading()
                }

                override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                    mView.onDataeror(t)
                    mView.onHideloading()
                }

            })

    }
}