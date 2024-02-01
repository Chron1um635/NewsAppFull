package com.example.newsappfull.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.newsappfull.databinding.FragmentNewsBinding
import com.example.newsappfull.models.NewsModel
import com.squareup.picasso.Picasso


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val newsModel: NewsModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //val newsModelIndex: Int  = arguments?.getInt("index")!!
        Log.d("MyLog", "${newsModel.news.value}")
//        newsModel.news.observe(viewLifecycleOwner) {
//            binding.tvTitle.text = newsModel.news.value!![newsModelIndex].title
//            binding.tvDesc.text = newsModel.news.value!![newsModelIndex].description
//            binding.tvDatePublish.text = newsModel.news.value!![newsModelIndex].publishedAt
//            binding.tvSourcePublish.text = newsModel.news.value!![newsModelIndex].source.name
//            binding.tvUrl.text = newsModel.news.value!![newsModelIndex].url
//            Picasso.get().load(newsModel.news.value!![newsModelIndex].urlToImage).into(binding.imNews)
//        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
        fun getNewInstance(args: Bundle?): NewsFragment {
            val newsFragment = NewsFragment()
            newsFragment.arguments = args
            return newsFragment
        }
    }
}