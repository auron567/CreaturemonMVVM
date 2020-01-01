package com.example.creaturemonmvvm.app

import android.app.Application
import com.example.creaturemonmvvm.di.AppComponent
import com.example.creaturemonmvvm.di.DaggerAppComponent

class CreaturemonApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}