package com.kjbriyan.socialapps.ui.additem

import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.ResponseStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostPresenter (private val mView : AdditemView) {
    private val TAG ="AddItem"

    fun addItem(nama : String, keterangan : String, img : String?,iduser : String?){
        mView.onShowloading()
        Initretrofit().getInstance().insertpost(nama,keterangan,img,iduser)
            .enqueue(object : Callback<ResponseStatus> {
                override fun onResponse(
                    call: Call<ResponseStatus>,
                    response: Response<ResponseStatus>
                ) {
                    val res = response.body()
                    mView.onDatasend(res)
                    mView.onHideloading()
                }

                override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                    mView.onDataeror(t)
                    mView.onHideloading()
                }

            })

    }

}