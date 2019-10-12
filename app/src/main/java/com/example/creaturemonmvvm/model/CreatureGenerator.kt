package com.example.creaturemonmvvm.model

class CreatureGenerator {

    fun generateCreature(attributes: CreatureAttributes, name: String = "", drawable: Int = 0): Creature {
        val hitPoints = with(attributes) {
            5 * intelligence + 3 * strength + 4 * endurance
        }

        return Creature(attributes, hitPoints, name, drawable)
    }
}