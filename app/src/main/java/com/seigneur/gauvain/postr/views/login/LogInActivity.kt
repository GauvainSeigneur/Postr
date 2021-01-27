package com.seigneur.gauvain.postr.views.login

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.views.MainActivity
import com.seigneur.gauvain.presentation.login.LogInViewModel
import com.seigneur.gauvain.presentation.common.model.livedata.LiveDataState
import kotlinx.android.synthetic.main.activity_log_in.*

import org.koin.android.viewmodel.ext.android.viewModel

class LogInActivity : AppCompatActivity() {

    private val viewModel: LogInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        progressBar.max = 100
        webView.loadUrl(viewModel.authUri)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                webRessource: WebResourceRequest
            ): Boolean {
                viewModel.checkAuthUrl(webRessource.url)
                return super.shouldOverrideUrlLoading(view, webRessource)
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                //todo - chnage progress
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                //todo - change progress
            }
        }
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.loginData.observe(this, Observer {
            when (it) {
                is LiveDataState.Success -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is LiveDataState.Error -> {

                }
            }
        })
    }

}
