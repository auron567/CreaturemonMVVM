package com.example.creaturemonmvvm.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        AppSubcomponents::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }

    fun allCreaturesComponent(): AllCreaturesComponent.Factory

    fun creatureComponent(): CreatureComponent.Factory
}