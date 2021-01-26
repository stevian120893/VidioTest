package com.vidiotest.activity.item_detail


import com.vidiotest.bases.MvpView
import com.vidiotest.models.JokeModel

interface ItemDetailMvp {

    interface View: MvpView {

        fun initUi()

        fun initListener()

        fun showJoke(jokeModel: JokeModel)

        fun startShimmer()

    }

    interface Presenter {

        fun getRandomJoke()

        fun getRandomJokeByCategory(category: String)

    }

}