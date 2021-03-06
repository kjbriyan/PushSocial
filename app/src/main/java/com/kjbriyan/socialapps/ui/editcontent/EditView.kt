package com.kjbriyan.socialapps.ui.editcontent

import com.kjbriyan.socialapps.ResponseStatus

interface EditView {
    fun onShowloading()
    fun onHideloading()
    fun onDatasend(result : ResponseStatus?)
    fun onDataeror(t : Throwable)
}