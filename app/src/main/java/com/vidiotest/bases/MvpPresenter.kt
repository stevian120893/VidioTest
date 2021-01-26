package com.vidiotest.bases

interface MvpPresenter<V: MvpView> {

    fun onAttachView(mvpView: V)

    fun onDetachView()

}