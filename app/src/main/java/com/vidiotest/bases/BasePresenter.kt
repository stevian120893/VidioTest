package com.vidiotest.bases

import android.content.Context
import com.vidiotest.api.ApiManager

open class BasePresenter<V: MvpView>(val context: Context): MvpPresenter<V> {

    val apiManager = ApiManager(context)
    var mvpView: V? = null

    override fun onAttachView(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetachView() {
        mvpView = null
    }

}