package com.example.newsappupdate.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappfull.R
import com.example.newsappfull.data.NewsData
import com.example.newsappfull.databinding.NewsItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter(val listener: Listener): ListAdapter<NewsData, NewsAdapter.Holder>(Comporator()) {
    class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = NewsItemBinding.bind(view)
        fun bind(news: NewsData, listener: Listener) = with(binding){
            tvTitleMain.text = news.title
            tvCounter.text = news.counter.toString()
            if (news.urlToImage?.isEmpty() == false) {
                Picasso.get().load(news.urlToImage).into(imNewsMain)
            } else {
                imNewsMain.setImageResource(R.drawable.no_image)
            }
            itemView.setOnClickListener {
                news.counter++
                tvCounter.text = news.counter.toString()
                listener.onClick(news)
            }
        }

    }

    class Comporator: DiffUtil.ItemCallback<NewsData>() {
        override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface Listener {
        fun onClick(newsItem: NewsData){

        }
    }

}