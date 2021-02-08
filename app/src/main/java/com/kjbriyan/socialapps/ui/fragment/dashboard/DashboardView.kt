package com.kjbriyan.socialapps.ui.fragment.dashboard

import com.kjbriyan.socialapps.model.DataItemss

interface DashboardView {

    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results : List<DataItemss>?)
    fun onDataeror (message : Throwable)

}