package com.vidiotest.activity.category_list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vidiotest.R
import com.vidiotest.utils.inflate
import com.vidiotest.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_product_category.view.*

class CategoryAdapter(val mList: ArrayList<String>, val mListener: HomeCategoryListener): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(parent.inflate(R.layout.item_product_category, false), mListener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(mList[position])
    }

    interface HomeCategoryListener {
        fun onCategoryClick(id: String?)
    }

    inner class CategoryViewHolder(val mView: View, val mListener: HomeCategoryListener): RecyclerView.ViewHolder(mView) {

        fun bindView(category: String) {
            mView.tvTitle.text =  category.substring(0, 1). toUpperCase() + category.substring(1)

            mView.setSafeOnClickListener {
                mListener.onCategoryClick(category)
            }
        }

    }
}