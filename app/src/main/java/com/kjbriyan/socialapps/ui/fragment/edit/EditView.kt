package com.kjbriyan.socialapps.ui.fragment.edit

import com.kjbriyan.socialapps.model.DataItemss


interface EditView {
    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results : List<DataItemss>?)
    fun onDataeror (message : Throwable)
}