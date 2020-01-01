package com.example.creaturemonmvvm.di

import android.app.Application
import androidx.room.Room
import com.example.creaturemonmvvm.model.room.CreatureDao
import com.example.creaturemonmvvm.model.room.CreatureDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCreatureDatabase(application: Application): CreatureDatabase {
        return Room.databaseBuilder(application, CreatureDatabase::class.java, "creature_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideCreatureDao(creatureDatabase: CreatureDatabase): CreatureDao {
        return creatureDatabase.creatureDao()
    }
}