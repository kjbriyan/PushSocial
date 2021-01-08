package com.kjbriyan.socialapps.ui.additem

import com.kjbriyan.socialapps.ResponseStatus

interface AdditemView {
    fun onShowloading()
    fun onHideloading()
    fun onDatasend(result : ResponseStatus?)
    fun onDataeror(t : Throwable)
}