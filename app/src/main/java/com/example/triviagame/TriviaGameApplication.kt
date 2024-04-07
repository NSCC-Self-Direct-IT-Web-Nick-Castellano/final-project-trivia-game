package com.example.triviagame

import android.app.Application
import com.example.triviagame.data.AppContainer
import com.example.triviagame.data.DefaultAppContainer

class TriviaGameApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Initialize the repository
        container = DefaultAppContainer(this)
    }

}