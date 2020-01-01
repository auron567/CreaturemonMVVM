package com.example.creaturemonmvvm.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.creaturemonmvvm.viewmodel.AllCreaturesViewModel
import com.example.creaturemonmvvm.viewmodel.CreatureViewModel
import com.example.creaturemonmvvm.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllCreaturesViewModel::class)
    abstract fun allCreaturesViewModel(allCreaturesViewModel: AllCreaturesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatureViewModel::class)
    abstract fun creatureViewModel(creatureViewModel: CreatureViewModel): ViewModel

    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}