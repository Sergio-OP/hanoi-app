package com.sosorio.hanoiapp.domain.entities

import com.sosorio.hanoiapp.utils.generateRandomLongColor

class HanoiGame(
    numberOfDisks: Int,
) {
    private val _towers: HanoiTowers = createTowers(numberOfDisks)
    val towers: HanoiTowers
        get() = _towers

    private fun createTowers(numberOfDisks: Int): HanoiTowers {
        val towers = createEmptyTowers()
        towers[0].addAll(generateDisks(numberOfDisks))
        return towers
    }

    private fun createEmptyTowers(): HanoiTowers = List(NUMBER_OF_RODS) { ArrayDeque() }

    private fun generateDisks(numberOfDisks: Int): List<HanoiDisk> =
        List(numberOfDisks) {
            HanoiDisk(
                index = it + 1,
                color = generateRandomLongColor(),
            )
        }

    fun moveDisk(
        from: Int,
        to: Int,
    ): Result<Unit> {
        val disk = _towers[from].firstOrNull() ?: return Result.failure(IllegalArgumentException("No disk to move"))
        val topDisk = _towers[to].firstOrNull()

        if (topDisk != null && topDisk.index <= disk.index) return Result.failure(IllegalArgumentException("No valid movement"))

        _towers[from].removeFirst()
        _towers[to].addFirst(disk)
        return Result.success(Unit)
    }

    companion object {
        private const val NUMBER_OF_RODS = 3
    }
}
