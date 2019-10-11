package com.example.creaturemonmvvm.model

data class AttributeValue(val name: String = "", val value: Int = 0) {

    override fun toString() = "$name: $value"
}