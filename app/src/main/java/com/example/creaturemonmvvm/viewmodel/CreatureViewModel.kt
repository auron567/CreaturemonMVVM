package com.example.creaturemonmvvm.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creaturemonmvvm.app.Event
import com.example.creaturemonmvvm.model.*

class CreatureViewModel(
    private val generator: CreatureGenerator,
    private val repository: CreatureRepository
) : ViewModel() {

    private val saveLiveData = MutableLiveData<Event<Boolean>>()
    private val creatureLiveData = MutableLiveData<Creature>()
    lateinit var creature: Creature

    var name = ""
    var intelligence = 0
    var strength = 0
    var endurance = 0
    var drawable = 0

    fun getSaveLiveData(): LiveData<Event<Boolean>> {
        return saveLiveData
    }

    fun getCreatureLiveData(): LiveData<Creature> {
        return creatureLiveData
    }

    fun attributeSelected(attributeType: AttributeType, position: Int) {
        when (attributeType) {
            AttributeType.INTELLIGENCE ->
                intelligence = AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH ->
                strength = AttributeStore.STRENGTH[position].value
            AttributeType.ENDURANCE ->
                endurance = AttributeStore.ENDURANCE[position].value
        }

        updateCreature()
    }

    fun nameChanged(name: String) {
        this.name = name
        updateCreature()
    }

    fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        updateCreature()
    }

    fun isDrawableSelected(): Boolean {
        return drawable != 0
    }

    fun saveCreature() {
        if (canSaveCreature()) {
            repository.saveCreature(creature)
            saveLiveData.postValue(Event(true))
        } else {
            saveLiveData.postValue(Event(false))
        }
    }

    @VisibleForTesting
    fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = generator.generateCreature(attributes, name, drawable)
        creatureLiveData.postValue(creature)
    }

    @VisibleForTesting
    fun canSaveCreature(): Boolean {
        return intelligence != 0 && strength != 0 && endurance != 0 &&
                drawable != 0 && name.isNotBlank()
    }
}