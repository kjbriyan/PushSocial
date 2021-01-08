package com.kjbriyan.socialapps.ui.fragment.dashboard

import com.kjbriyan.socialapps.model.DataItems

interface DashboardView {

    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results : List<DataItems>?)
    fun onDataeror (message : Throwable)

}