package com.vidiotest.activity.category_detail

import android.content.Context
import com.vidiotest.api.ApiListener
import com.vidiotest.bases.BasePresenter
import com.vidiotest.models.JokeModel

class CategoryDetailPresenter(context: Context): BasePresenter<CategoryDetailMvp.View>(context), CategoryDetailMvp.Presenter {

    override fun getRandomJokeByCategory(category: String) {
        mvpView?.run {
            apiManager.getRandomJokeByCategory(category, object: ApiListener<JokeModel> {
                override fun onSuccess(response: JokeModel) {
                    response.run {
                        showRandomJokeByCategory(this)
                    }
                }

                override fun onFailed(message: String?) {
                }
            })
        }
    }

}