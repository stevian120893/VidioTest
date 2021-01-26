package com.vidiotest.activity.category_list

import com.vidiotest.bases.MvpView

interface CategoryListMvp {

    interface View: MvpView {

        fun initUi()

        fun initListener()

        fun startShimmer()

        fun showJokeCategory(categoryModel: ArrayList<String>)

    }

    interface Presenter {

        fun getJokeCategory()

    }

}