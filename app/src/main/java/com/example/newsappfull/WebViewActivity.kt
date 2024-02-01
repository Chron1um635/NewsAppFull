package com.example.newsappfull

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.newsappfull.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webViewSetup()
    }

    private fun webViewSetup(){
        val url = intent.getStringExtra("url").toString()
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url)
    }
}