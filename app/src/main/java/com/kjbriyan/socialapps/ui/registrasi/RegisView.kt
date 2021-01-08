package com.kjbriyan.socialapps.ui.registrasi

import com.kjbriyan.socialapps.ResponseStatus

interface RegisView {
    fun onLoading()
    fun onHideloading()
    fun onDatasukses(t : ResponseStatus?)
    fun onDataeror(t : Throwable)
}