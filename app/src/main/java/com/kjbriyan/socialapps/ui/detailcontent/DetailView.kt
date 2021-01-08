package com.kjbriyan.socialapps.ui.detailcontent

import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.model.DataItems
import com.kjbriyan.socialapps.model.KetItem
import com.kjbriyan.socialapps.model.ResponsePosting

interface DetailView {
    fun onLoading()
    fun onHideloading()
    fun onDatasukses(t : List<KetItem>?)
    fun onSendSukses(t : ResponseStatus?)
    fun onDataeror(t : Throwable)
}