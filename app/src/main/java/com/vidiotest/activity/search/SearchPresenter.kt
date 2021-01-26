package com.vidiotest.activity.search

import android.content.Context
import com.vidiotest.api.ApiListener
import com.vidiotest.bases.BasePresenter
import com.vidiotest.models.SearchJokeModel

class SearchPresenter(context: Context): BasePresenter<SearchMvp.View>(context), SearchMvp.Presenter {

    override fun getJokeByKeyword(keyword: String) {
        mvpView?.run {
            apiManager.getJokeByKeyword(keyword, object: ApiListener<SearchJokeModel> {
                override fun onSuccess(response: SearchJokeModel) {
                    response.run {
                        showJoke(this)
                    }
                }

                override fun onFailed(message: String?) {
                }
            })
        }
    }

}