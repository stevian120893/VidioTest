package com.vidiotest.api

interface ApiListener<T> {

    fun onSuccess(response: T)

    fun onFailed(message: String?)

}