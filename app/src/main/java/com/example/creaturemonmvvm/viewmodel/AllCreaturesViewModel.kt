package com.example.creaturemonmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.creaturemonmvvm.model.CreatureRepository

class AllCreaturesViewModel(private val repository: CreatureRepository) : ViewModel() {
    val allCreaturesLiveData = repository.getAllCreatures()

    fun clearAllCreatures() {
        repository.clearAllCreatures()
    }
}