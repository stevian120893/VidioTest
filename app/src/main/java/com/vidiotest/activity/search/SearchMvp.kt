package com.vidiotest.activity.search

import com.vidiotest.bases.MvpView
import com.vidiotest.models.SearchJokeModel

interface SearchMvp {

    interface View: MvpView {

        fun initUi()

        fun initListener()

        fun startShimmer()

        fun showJoke(searchJokeModel: SearchJokeModel)

    }

    interface Presenter {

        fun getJokeByKeyword(keyword: String)

    }

}