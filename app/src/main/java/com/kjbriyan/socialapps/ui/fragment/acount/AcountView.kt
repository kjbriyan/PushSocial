package com.kjbriyan.socialapps.ui.fragment.acount

import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.model.DataItems

interface AcountView {
    fun onLoading()
    fun onHideloading()
    fun onSendSukses(t : ResponseStatus?)
    fun onDataeror(t : Throwable)
}