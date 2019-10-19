package com.example.creaturemonmvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.creaturemonmvvm.model.Creature
import com.example.creaturemonmvvm.model.CreatureAttributes
import com.example.creaturemonmvvm.model.CreatureGenerator
import com.example.creaturemonmvvm.model.CreatureRepository
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
    private val mockRepository: CreatureRepository = mockk()
    private lateinit var creatureViewModel: CreatureViewModel

    @Before
    fun setup() {
        creatureViewModel = CreatureViewModel(mockGenerator, mockRepository)
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

    @Test
    fun testCantSaveCreatureWithBlankName() {
        with(creatureViewModel) {
            intelligence = 10
            strength = 3
            endurance = 7
            drawable = 2131165292
            name = ""
        }

        assertEquals(false, creatureViewModel.canSaveCreature())
    }

    @Test
    fun testCantSaveCreatureWithoutIntelligence() {
        with(creatureViewModel) {
            intelligence = 0
            strength = 3
            endurance = 7
            drawable = 2131165292
            name = "Test Creature"
        }

        assertEquals(false, creatureViewModel.canSaveCreature())
    }

    @Test
    fun testCantSaveCreatureWithoutStrength() {
        with(creatureViewModel) {
            intelligence = 10
            strength = 0
            endurance = 7
            drawable = 2131165292
            name = "Test Creature"
        }

        assertEquals(false, creatureViewModel.canSaveCreature())
    }

    @Test
    fun testCantSaveCreatureWithoutEndurance() {
        with(creatureViewModel) {
            intelligence = 10
            strength = 3
            endurance = 0
            drawable = 2131165292
            name = "Test Creature"
        }

        assertEquals(false, creatureViewModel.canSaveCreature())
    }

    @Test
    fun testCantSaveCreatureWithoutDrawable() {
        with(creatureViewModel) {
            intelligence = 10
            strength = 3
            endurance = 7
            drawable = 0
            name = "Test Creature"
        }

        assertEquals(false, creatureViewModel.canSaveCreature())
    }
}