package com.vidiotest.activity.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vidiotest.R
import com.vidiotest.activity.category_detail.RandomJokeAdapter
import com.vidiotest.activity.item_detail.ItemDetailActivity
import com.vidiotest.bases.BaseActivity
import com.vidiotest.models.JokeModel
import com.vidiotest.models.SearchJokeModel
import com.vidiotest.utils.hide
import com.vidiotest.utils.setSafeOnClickListener
import com.vidiotest.utils.show
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(), SearchMvp.View {

    private lateinit var mPresenter: SearchPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mPresenter = SearchPresenter(this)
        mPresenter.onAttachView(this)

        initUi()
        initListener()
    }

    override fun initUi() {

    }

    override fun initListener() {
        ivSearch.setSafeOnClickListener {
            if(!etSearch.text.toString().equals("")) {
                startShimmer()
                mPresenter.getJokeByKeyword(etSearch.text.toString())
            } else {
                Toast.makeText(this, "Please fill the blank space", Toast.LENGTH_SHORT).show()
            }
        }

        etSearch.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(!etSearch.text.toString().equals("")) {
                    startShimmer()
                    mPresenter.getJokeByKeyword(etSearch.text.toString())
                } else {
                    Toast.makeText(this, "Please fill the blank space", Toast.LENGTH_SHORT).show()
                }
                handled = true
            }
            handled
        }

        ivArrowBack.setSafeOnClickListener { finish() }
    }

    override fun startShimmer() {
        sflSearch.show()
        rvJokeByKeyword.visibility = View.GONE
    }

    override fun showJoke(searchJokeModel: SearchJokeModel) {
        rvJokeByKeyword.visibility = View.VISIBLE
        sflSearch.hide()
        val jokeAdapter = RandomJokeAdapter(searchJokeModel.listJoke!!, object: RandomJokeAdapter.RandomJokeListener {
            override fun onCategoryClick(jokeModel: JokeModel) {
                this.run {
                    val intent = ItemDetailActivity.newIntent(this@SearchActivity)
                    intent.putExtra("joke_model", jokeModel)
                    intent.putExtra("from", "category_detail")
                    startActivity(intent)
                }
            }
        })
        rvJokeByKeyword.adapter = jokeAdapter
        rvJokeByKeyword.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}