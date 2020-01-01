package com.example.creaturemonmvvm.di

import com.example.creaturemonmvvm.model.CreatureRepository
import com.example.creaturemonmvvm.model.room.RoomRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideCreatureRepository(repository: RoomRepository): CreatureRepository
}