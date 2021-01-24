package com.seigneur.gauvain.postr.views.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.views.MainActivity
import com.seigneur.gauvain.postr.views.login.LogInActivity
import com.seigneur.gauvain.presentation.SplashViewModel
import com.seigneur.gauvain.presentation.model.SplashData
import com.seigneur.gauvain.presentation.model.livedata.LiveDataState
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.splashData.observe(this, Observer {
            when (it) {
                is LiveDataState.Success -> {
                    when (it.data) {
                        SplashData.AUTHENTICATED -> {
                            sendToRelatedActivity(MainActivity::class)
                        }
                        SplashData.NOT_AUTHENTICATED -> {
                            sendToRelatedActivity(LogInActivity::class)
                        }
                    }
                }
                else -> {
                    //do nothing in case of error
                }
            }
        })
    }

    private fun <T : AppCompatActivity> sendToRelatedActivity(activity: KClass<T>) {
        val intent = Intent(this, activity.java)
        startActivity(intent)
        finish()
    }
}