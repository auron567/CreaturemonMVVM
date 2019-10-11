package com.example.creaturemonmvvm.model

object AttributeStore {
    val INTELLIGENCE: List<AttributeValue> by lazy {
        mutableListOf<AttributeValue>().apply {
            add(AttributeValue("None"))
            add(AttributeValue("Aristotle", 3))
            add(AttributeValue("Newton", 7))
            add(AttributeValue("Einstein", 10))
        }
    }
    val STRENGTH: List<AttributeValue> by lazy {
        mutableListOf<AttributeValue>().apply {
            add(AttributeValue("None"))
            add(AttributeValue("Thor", 3))
            add(AttributeValue("Hulk", 7))
            add(AttributeValue("Hercules", 10))
        }
    }
    val ENDURANCE: List<AttributeValue> by lazy {
        mutableListOf<AttributeValue>().apply {
            add(AttributeValue("None"))
            add(AttributeValue("Aluminum", 3))
            add(AttributeValue("Gold", 7))
            add(AttributeValue("Iron", 10))
        }
    }
}