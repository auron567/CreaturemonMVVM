package com.example.creaturemonmvvm.model.room

import androidx.room.TypeConverter
import com.example.creaturemonmvvm.model.CreatureAttributes

class CreatureAttributesConverter {

    @TypeConverter
    fun fromCreatureAttributes(attributes: CreatureAttributes?): String? {
        return attributes?.let {
            "${it.intelligence},${it.strength},${it.endurance}"
        }
    }

    @TypeConverter
    fun toCreatureAttributes(value: String?): CreatureAttributes? {
        return value?.let {
            val pieces = it.split(",")
            CreatureAttributes(
                intelligence = pieces[0].toInt(),
                strength = pieces[1].toInt(),
                endurance = pieces[2].toInt()
            )
        }
    }
}