package com.vidiotest.activity.category_list

import android.content.Context
import com.vidiotest.api.ApiListener
import com.vidiotest.bases.BasePresenter

class CategoryListPresenter(context: Context): BasePresenter<CategoryListMvp.View>(context), CategoryListMvp.Presenter {

    override fun getJokeCategory() {
        mvpView?.run {
            apiManager.getJokeCategory(object: ApiListener<ArrayList<String>> {
                override fun onSuccess(response: ArrayList<String>) {
                    response.run {
                        showJokeCategory(this)
                    }
                }

                override fun onFailed(message: String?) {
                }
            })
        }
    }

}