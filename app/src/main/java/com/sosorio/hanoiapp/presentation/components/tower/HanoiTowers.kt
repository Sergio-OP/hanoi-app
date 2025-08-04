package com.sosorio.hanoiapp.presentation.components.tower

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.sosorio.hanoiapp.domain.entities.HanoiTowers
import com.sosorio.hanoiapp.domain.entities.numberOfDisks
import com.sosorio.hanoiapp.domain.entities.numberOfRods

@Composable
fun HanoiTowers(
    board: HanoiTowers,
    modifier: Modifier = Modifier,
) {
    val numberOfRods = board.numberOfRods
    val numberOfTotalDisks = board.numberOfDisks

    val localDensity = LocalDensity.current
    var totalHeight by remember { mutableStateOf(0.dp) }
    var totalWidth by remember { mutableStateOf(0.dp) }
    val minDiskHeight = totalHeight.div(numberOfTotalDisks)
    val maxDiskHeight = 20.dp
    val diskHeight = minOf(minDiskHeight, maxDiskHeight)
    val towerWidth = totalWidth.div(numberOfRods)

    Box(
        modifier =
            modifier
                .onGloballyPositioned { coordinates ->
                    totalHeight = with(localDensity) { coordinates.size.height.toDp() }
                    totalWidth = with(localDensity) { coordinates.size.width.toDp() }
                },
        contentAlignment = Alignment.BottomStart,
    ) {
        board.forEachIndexed { rodIndex, rod ->
            rod.forEachIndexed { diskIndex, disk ->
                val diskWidthFraction = disk.index.toFloat() / numberOfTotalDisks
                val diskWidth = towerWidth * diskWidthFraction

                val xOffset = (towerWidth * (rodIndex)) + ((towerWidth - diskWidth) / 2)
                val yOffset = -diskHeight * (rod.size - diskIndex)

                val offset by animateIntOffsetAsState(
                    IntOffset(
                        x = with(localDensity) { xOffset.toPx() }.toInt(),
                        y = with(localDensity) { yOffset.toPx() }.toInt(),
                    ),
                )

                key(disk.index) {
                    HanoiDisk(
                        disk = disk,
                        modifier =
                            Modifier
                                .width(diskWidth)
                                .height(diskHeight)
                                .offset { offset },
                    )
                }
            }
        }
    }
}
