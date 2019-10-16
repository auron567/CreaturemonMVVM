package com.example.creaturemonmvvm.di

import android.app.Application
import androidx.room.Room
import com.example.creaturemonmvvm.model.CreatureGenerator
import com.example.creaturemonmvvm.model.CreatureRepository
import com.example.creaturemonmvvm.model.room.CreatureDao
import com.example.creaturemonmvvm.model.room.CreatureDatabase
import com.example.creaturemonmvvm.model.room.RoomRepository
import com.example.creaturemonmvvm.viewmodel.CreatureViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // CreatureDatabase instance
    single { provideCreatureDatabase(androidApplication()) }
    // CreatureDao instance
    single { provideCreatureDao(get()) }
    // CreatureRepository instance
    single<CreatureRepository> { provideRoomRepository(get()) }
    // CreatureViewModel instance
    viewModel { provideCreatureViewModel(CreatureGenerator()) }
}

private fun provideCreatureDatabase(application: Application): CreatureDatabase {
    return Room.databaseBuilder(application, CreatureDatabase::class.java, "creature_database")
        .build()
}

private fun provideCreatureDao(creatureDatabase: CreatureDatabase): CreatureDao {
    return creatureDatabase.creatureDao()
}

private fun provideRoomRepository(creatureDao: CreatureDao): RoomRepository {
    return RoomRepository(creatureDao)
}

private fun provideCreatureViewModel(generator: CreatureGenerator): CreatureViewModel {
    return CreatureViewModel(generator)
}