package com.vidiotest.activity.item_detail

import android.content.Context
import com.vidiotest.api.ApiListener
import com.vidiotest.bases.BasePresenter
import com.vidiotest.models.JokeModel

class ItemDetailPresenter(context: Context): BasePresenter<ItemDetailMvp.View>(context), ItemDetailMvp.Presenter {

    override fun getRandomJoke() {
        mvpView?.run {
            apiManager.getRandomJoke(object: ApiListener<JokeModel> {
                override fun onSuccess(response: JokeModel) {
                    response.run {
                        showJoke(this)
                    }
                }

                override fun onFailed(message: String?) {
                }
            })
        }
    }

    override fun getRandomJokeByCategory(category: String) {
        mvpView?.run {
            apiManager.getRandomJokeByCategory(category, object: ApiListener<JokeModel> {
                override fun onSuccess(response: JokeModel) {
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