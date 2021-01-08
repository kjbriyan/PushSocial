package com.kjbriyan.socialapps.ui.login

import com.kjbriyan.socialapps.DataItem



interface LoginView{
    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results : List<DataItem?>)
    fun onDataeror (message : Throwable)

}