package com.sosorio.hanoiapp.domain.entities

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HanoiGameTest {
    private lateinit var game: HanoiGame

    @Before
    fun setUp() {
        game = HanoiGame(NUMBER_OF_DISKS)
    }

    @Test
    fun gameCreated_initialState_correctNumberOfRodsAndDisks() {
        assertEquals(NUMBER_OF_DISKS, game.towers.numberOfDisks)
        assertEquals(NUMBER_OF_RODS, game.towers.numberOfRods)
    }

    @Test
    fun gameCreated_initialState_correctDisksOrder() {
        val expectedUpperDiskIndex = 1
        val expectedLowerDiskIndex = NUMBER_OF_DISKS

        assertEquals(expectedUpperDiskIndex, game.towers[0].first().index)
        assertEquals(expectedLowerDiskIndex, game.towers[0].last().index)
    }

    @Test
    fun gameCreated_performMovements_disksMovedCorrectly() {
        game.moveDisk(0, 1)
        game.moveDisk(0, 2)

        assertEquals(3, game.towers[0].size)
        assertEquals(1, game.towers[1].size)
        assertEquals(1, game.towers[2].size)
    }

    @Test
    fun emptyTower_performMovement_nothingHappens() {
        game.moveDisk(2, 1)
        assertEquals(0, game.towers[1].size)
        assertEquals(0, game.towers[2].size)
    }

    companion object {
        private const val NUMBER_OF_DISKS = 5
        private const val NUMBER_OF_RODS = 3
    }
}
