package com.vidiotest.activity.category_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vidiotest.R
import com.vidiotest.activity.category_detail.CategoryDetailActivity
import com.vidiotest.activity.item_detail.ItemDetailActivity
import com.vidiotest.activity.search.SearchActivity
import com.vidiotest.bases.BaseActivity
import com.vidiotest.utils.hide
import com.vidiotest.utils.setSafeOnClickListener
import com.vidiotest.utils.show
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : BaseActivity(), CategoryListMvp.View {

    private lateinit var mPresenter: CategoryListPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CategoryListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        mPresenter = CategoryListPresenter(this)
        mPresenter.onAttachView(this)

        initUi()
        initListener()
    }

    override fun initUi() {
        startShimmer()
        mPresenter.getJokeCategory()
    }

    override fun initListener() {
        fabRandomJoke.setSafeOnClickListener {
            val intent = ItemDetailActivity.newIntent(this)
            intent.putExtra("from", "category_list")
            startActivity(intent)
        }

        llSearch.setSafeOnClickListener {
            val intent = SearchActivity.newIntent(this)
            startActivity(intent)
        }
    }

    override fun startShimmer() {
        sflCategoryList.show()
        llContent.visibility = View.GONE
    }

    override fun showJokeCategory(categoryModel: ArrayList<String>) {
        llContent.visibility = View.VISIBLE
        sflCategoryList.hide()
        val categoryAdapter = CategoryAdapter(categoryModel, object: CategoryAdapter.HomeCategoryListener {
            override fun onCategoryClick(id: String?) {
                this.run {
                    val intent = CategoryDetailActivity.newIntent(this@CategoryListActivity)
                    intent.putExtra("category_name", id)
                    startActivity(intent)
                }
            }
        })
        rvJokeCategory.adapter = categoryAdapter
        rvJokeCategory.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}