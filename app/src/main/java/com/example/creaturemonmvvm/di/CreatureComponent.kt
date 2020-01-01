package com.example.creaturemonmvvm.di

import com.example.creaturemonmvvm.view.creature.CreatureActivity
import dagger.Subcomponent

@Subcomponent
interface CreatureComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): CreatureComponent
    }

    fun inject(activity: CreatureActivity)
}