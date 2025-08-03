package com.sosorio.hanoiapp.domain.entities

typealias HanoiTowers = List<ArrayDeque<HanoiDisk>>

val HanoiTowers.numberOfRods: Int
    get() = this.size

val HanoiTowers.numberOfDisks: Int
    get() = sumOf { it.size }
