package com.example.creaturemonmvvm.di

import com.example.creaturemonmvvm.view.allcreatures.AllCreaturesActivity
import dagger.Subcomponent

@Subcomponent
interface AllCreaturesComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): AllCreaturesComponent
    }

    fun inject(activity: AllCreaturesActivity)
}