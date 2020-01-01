package com.example.creaturemonmvvm.model

import javax.inject.Inject

class CreatureGenerator @Inject constructor() {

    fun generateCreature(attributes: CreatureAttributes, name: String = "", drawable: Int = 0): Creature {
        val hitPoints = with(attributes) {
            5 * intelligence + 3 * strength + 4 * endurance
        }

        return Creature(attributes, hitPoints, name, drawable)
    }
}