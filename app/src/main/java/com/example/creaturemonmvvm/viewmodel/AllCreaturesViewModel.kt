package com.example.creaturemonmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.creaturemonmvvm.model.CreatureRepository
import javax.inject.Inject

class AllCreaturesViewModel @Inject constructor(
    private val repository: CreatureRepository
) : ViewModel() {

    val allCreaturesLiveData = repository.getAllCreatures()

    fun clearAllCreatures() {
        repository.clearAllCreatures()
    }
}