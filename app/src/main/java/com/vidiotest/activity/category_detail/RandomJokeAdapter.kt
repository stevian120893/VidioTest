package com.vidiotest.activity.category_detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vidiotest.R
import com.vidiotest.models.JokeModel
import com.vidiotest.utils.convertDate
import com.vidiotest.utils.inflate
import com.vidiotest.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_joke.view.*

class RandomJokeAdapter(val mList: ArrayList<JokeModel>, val mListener: RandomJokeListener): RecyclerView.Adapter<RandomJokeAdapter.RandomJokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomJokeViewHolder {
        return RandomJokeViewHolder(parent.inflate(R.layout.item_joke, false), mListener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RandomJokeViewHolder, position: Int) {
        holder.bindView(mList[position])
    }

    interface RandomJokeListener {
        fun onCategoryClick(jokeModel: JokeModel)
    }

    inner class RandomJokeViewHolder(val mView: View, val mListener: RandomJokeListener): RecyclerView.ViewHolder(mView) {

        fun bindView(jokeModel: JokeModel) {
            Glide.with(mView.context).load(jokeModel.iconUrl).into(mView.ivIcon)
            mView.tvValue.text = jokeModel.value
            mView.tvUpdatedAt.text = convertDate(jokeModel.updatedAt!!)

            mView.setSafeOnClickListener {
                mListener.onCategoryClick(jokeModel)
            }
        }

    }
}