package com.example.creaturemonmvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.creaturemonmvvm.model.Creature
import com.example.creaturemonmvvm.model.CreatureAttributes
import com.example.creaturemonmvvm.model.CreatureGenerator
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CreatureViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val mockGenerator: CreatureGenerator = mockk()
    private lateinit var creatureViewModel: CreatureViewModel

    @Before
    fun setup() {
        creatureViewModel = CreatureViewModel(mockGenerator)
    }

    @Test
    fun testSetupCreature() {
        val attributes = CreatureAttributes(10, 3, 7)
        val stubCreature = Creature(attributes, 87, "Test Creature")
        every { mockGenerator.generateCreature(attributes) } returns stubCreature

        with(creatureViewModel) {
            intelligence = 10
            strength = 3
            endurance = 7
            updateCreature()
        }

        assertEquals(stubCreature, creatureViewModel.creature)
    }
}