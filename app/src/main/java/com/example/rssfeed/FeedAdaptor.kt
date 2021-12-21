package com.example.rssfeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.databinding.FeedRowBinding

class FeedAdaptor(private var questions: List<Post>): RecyclerView.Adapter<FeedAdaptor.FeedViewHolder>() {
    class FeedViewHolder( val binding: FeedRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(FeedRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
       val question = questions[position]

        holder.binding.apply {
            titleTV.text = question.title
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    fun updateFeed(feed: List<Post>) {
        this.questions = feed
        notifyDataSetChanged()
    }
}