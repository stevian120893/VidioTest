package com.vidiotest.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.vidiotest.models.JokeModel
import com.vidiotest.models.SearchJokeModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager(context: Context) {

    private val mOkHttpClient: OkHttpClient
    private val mGsonConverterFactory: GsonConverterFactory
    private val mApiEndPoint: ApiEndPoint
    private val mApiHelper: ApiHelper

    init {
        mOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor(context))
            .build()
        mGsonConverterFactory = GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
                //.registerTypeAdapter(object: TypeToken<ResponseModel<Any>>(){}.type, ResponseModel.DataDeserializer<Any>())
                .create()
        )
        mApiEndPoint = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(mGsonConverterFactory)
            .client(mOkHttpClient)
            .build()
            .create(ApiEndPoint::class.java)
        mApiHelper = ApiHelper(context)
    }

    fun getJokeCategory(apiListener: ApiListener<ArrayList<String>>) {
        mApiHelper.callService({ mApiEndPoint.getJokeCategory() }, apiListener)
    }

    fun getRandomJoke(apiListener: ApiListener<JokeModel>) {
        mApiHelper.callService({ mApiEndPoint.getRandomJoke() }, apiListener)
    }

    fun getRandomJokeByCategory(category: String, apiListener: ApiListener<JokeModel>) {
        mApiHelper.callService({ mApiEndPoint.getRandomJokeByCategory(category) }, apiListener)
    }

    fun getJokeByKeyword(keyword: String, apiListener: ApiListener<SearchJokeModel>) {
        mApiHelper.callService({ mApiEndPoint.getJokeByKeyword(keyword) }, apiListener)
    }

}