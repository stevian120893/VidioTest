package com.vidiotest.activity.category_detail

import com.vidiotest.bases.MvpView
import com.vidiotest.models.JokeModel

interface CategoryDetailMvp {

    interface View: MvpView {

        fun initUi()

        fun initListener()

        fun startShimmer()

        fun showRandomJokeByCategory(jokeModel: JokeModel)

    }

    interface Presenter {

        fun getRandomJokeByCategory(category: String)

    }

}