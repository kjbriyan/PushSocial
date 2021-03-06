package com.kjbriyan.socialapps.ui.editcontent

import com.kjbriyan.socialapps.Initretrofit
import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.ui.additem.AdditemView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPresenter (private val mView : EditView) {


    fun updateItem(iduser : String,nama : String, keterangan : String, img : String){
        mView.onShowloading()
        Initretrofit().getInstance().contUpdate(iduser,nama,keterangan,img)
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