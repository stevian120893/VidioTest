package com.vidiotest.activity.category_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vidiotest.R
import com.vidiotest.activity.item_detail.ItemDetailActivity
import com.vidiotest.bases.BaseActivity
import com.vidiotest.models.JokeModel
import com.vidiotest.utils.hide
import com.vidiotest.utils.setSafeOnClickListener
import com.vidiotest.utils.show
import kotlinx.android.synthetic.main.activity_category_detail.*

class CategoryDetailActivity : BaseActivity(), CategoryDetailMvp.View {

    private lateinit var mPresenter: CategoryDetailPresenter
    private var mCategoryName: String? = null
    private var jokeModelArray: ArrayList<JokeModel>? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CategoryDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)

        mPresenter = CategoryDetailPresenter(this)
        mPresenter.onAttachView(this)

        initUi()
        initListener()
    }

    override fun initUi() {
        startShimmer()
        mCategoryName = intent.getStringExtra("category_name")
        mPresenter.getRandomJokeByCategory(mCategoryName!!)
    }

    override fun initListener() {
        ivArrowBack.setSafeOnClickListener { finish() }
    }

    override fun startShimmer() {
        sflCategoryDetail.show()
        llContent.visibility = View.GONE
    }

    override fun showRandomJokeByCategory(jokeModel: JokeModel) {
        tvCategory.text = mCategoryName!!.substring(0, 1). toUpperCase() + mCategoryName!!.substring(1)

        llContent.visibility = View.VISIBLE
        sflCategoryDetail.hide()

        jokeModelArray = ArrayList()
        jokeModelArray!!.add(jokeModel)

        val randomJokeAdapter = RandomJokeAdapter(jokeModelArray!!, object: RandomJokeAdapter.RandomJokeListener {
            override fun onCategoryClick(jokeModel: JokeModel) {
                this.run {
                    val intent = ItemDetailActivity.newIntent(this@CategoryDetailActivity)
                    intent.putExtra("joke_model", jokeModel)
                    intent.putExtra("from", "category_detail")
                    intent.putExtra("category_name", mCategoryName)
                    startActivity(intent)
                }
            }
        })
        rvRandomJokeByCategory.adapter = randomJokeAdapter
        rvRandomJokeByCategory.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}