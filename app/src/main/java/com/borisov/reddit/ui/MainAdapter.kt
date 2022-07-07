package com.borisov.reddit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.borisov.reddit.databinding.RedditPostRecyclerItemBinding
import com.borisov.reddit.domain.RedditPost

/**
 * @author Borisov Andrey on 07.07.2022
 **/
class MainAdapter :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var filmData: List<RedditPost> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = RedditPostRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filmData[position])
    }

    override fun getItemCount() = filmData.size

    inner class MainViewHolder(private val binding: RedditPostRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: RedditPost) {
            binding.apply {
                title.text = post.title
                score.text = post.score.toString()
                comments.text = post.comments.toString()
            }
        }
    }

    fun setData(data: List<RedditPost>) {
        filmData = filmData + data
        notifyDataSetChanged()
    }
}