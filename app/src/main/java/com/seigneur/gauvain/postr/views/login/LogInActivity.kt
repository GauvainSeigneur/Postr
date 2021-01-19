package com.seigneur.gauvain.postr.views.login

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.seigneur.gauvain.postr.R
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    //private val mLogInViewModel by viewModel<LogInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        progressBar.max = 100
        webView.loadUrl("")
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, webRessource: WebResourceRequest): Boolean {
               // mLogInViewModel.checkAuthUrl(webRessource.url)
                val keyCode = webRessource.url.getQueryParameter("code")
                Log.d("lolilol", "code $keyCode")
                return super.shouldOverrideUrlLoading(view, webRessource)
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                //mLogInViewModel.mWebProgressValue.value = 0
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                //mLogInViewModel.mWebProgressValue.value = newProgress
            }
        }
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {


    }

}
