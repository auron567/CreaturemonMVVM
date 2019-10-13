package com.example.creaturemonmvvm.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.creaturemonmvvm.model.Creature
import com.example.creaturemonmvvm.model.CreatureRepository

class RoomRepository(private val creatureDao: CreatureDao) : CreatureRepository {

    override fun saveCreature(creature: Creature) {
        InsertAsyncTask(creatureDao).execute(creature)
    }

    override fun getAllCreatures(): LiveData<List<Creature>> {
        return creatureDao.getAllCreatures()
    }

    override fun clearAllCreatures() {
        DeleteAsyncTask(creatureDao).execute()
    }

    private class InsertAsyncTask(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {

        override fun doInBackground(vararg params: Creature): Void? {
            dao.insert(params[0])
            return null
        }
    }

    private class DeleteAsyncTask(private val dao: CreatureDao) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {
            dao.clearAllCreatures()
            return null
        }
    }
}