package com.kjbriyan.socialapps.ui.detailcontent

import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.model.*

interface DetailView {
    fun onLoading()
    fun onHideloading()
    fun onDatasukses(t : List<KetItem>?)
    fun onSendSukses(t : ResponseStatus?)
    fun onLikeCon(t : List<DataIteem>?)
    fun onDataeror(t : Throwable)
}