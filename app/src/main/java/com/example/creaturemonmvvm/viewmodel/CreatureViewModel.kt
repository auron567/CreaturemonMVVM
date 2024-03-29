package com.example.creaturemonmvvm.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creaturemonmvvm.app.Event
import com.example.creaturemonmvvm.model.*

class CreatureViewModel(
    private val generator: CreatureGenerator,
    private val repository: CreatureRepository
) : ViewModel() {

    private val _saveLiveData = MutableLiveData<Event<Boolean>>()
    private val _creatureLiveData = MutableLiveData<Creature>()

    val saveLiveData: LiveData<Event<Boolean>>
        get() = _saveLiveData
    val creatureLiveData: LiveData<Creature>
        get() = _creatureLiveData

    lateinit var creature: Creature
    var intelligence = 0
    var strength = 0
    var endurance = 0
    var drawable = 0
    val name = ObservableField<String>("").apply {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                updateCreature()
            }
        })
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
            _saveLiveData.postValue(Event(true))
        } else {
            _saveLiveData.postValue(Event(false))
        }
    }

    @VisibleForTesting
    fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = generator.generateCreature(attributes, name.get() ?: "", drawable)
        _creatureLiveData.postValue(creature)
    }

    @VisibleForTesting
    fun canSaveCreature(): Boolean {
        val name = name.get()
        name?.let {
            return intelligence != 0 && strength != 0 && endurance != 0 &&
                    drawable != 0 && name.isNotBlank()
        }
        return false
    }
}