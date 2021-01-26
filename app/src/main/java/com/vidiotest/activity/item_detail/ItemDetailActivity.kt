package com.vidiotest.activity.item_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.vidiotest.R
import com.vidiotest.activity.category_list.CategoryListActivity
import com.vidiotest.bases.BaseActivity
import com.vidiotest.models.JokeModel
import com.vidiotest.utils.hide
import com.vidiotest.utils.setSafeOnClickListener
import com.vidiotest.utils.show
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : BaseActivity(), ItemDetailMvp.View {

    private lateinit var mPresenter: ItemDetailPresenter
    private var mFrom: String? = null
    private var mJokeValue: String? = null
    private var mCategoryName: String? = null
    private var mJokeModel: JokeModel? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ItemDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        mPresenter = ItemDetailPresenter(this)
        mPresenter.onAttachView(this)

        initUi()
        initListener()
    }

    override fun startShimmer() {
        sflItemDetail.show()
        llContent.visibility = View.GONE
    }

    override fun initUi() {
        mFrom = intent.getStringExtra("from")
        mCategoryName = intent.getStringExtra("category_name")

        startShimmer()

        if(mFrom.equals("category_list"))
            mPresenter.getRandomJoke()
        else if(mFrom.equals("category_detail")) {
            mJokeModel = intent.getParcelableExtra("joke_model")
            showJoke(mJokeModel!!)
        }

    }

    override fun initListener() {
        ivHome.setSafeOnClickListener {
            val intent = CategoryListActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

        ivShare.setSafeOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
                var shareMessage = "\nLet me recommend you this application\n\n"
                shareMessage = mJokeValue!!
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Choose one"))
            } catch (e: Exception) {
               print(e.stackTrace)
            }
        }

        ivRandom.setSafeOnClickListener {
            if(mFrom.equals("category_list")) { // random jokes
                startShimmer()
                mPresenter.getRandomJoke()
            }else if(mFrom.equals("category_detail")) { // has a category
                startShimmer()
                mPresenter.getRandomJokeByCategory(mCategoryName!!)
            }

        }

        ivArrowBack.setSafeOnClickListener { finish() }
    }

    override fun showJoke(jokeModel: JokeModel) {
        llContent.visibility = View.VISIBLE
        sflItemDetail.hide()

        mJokeValue = jokeModel.value

        Glide.with(this).load(jokeModel.iconUrl).into(ivIcon)
        tvValue.text = jokeModel.value
    }
}