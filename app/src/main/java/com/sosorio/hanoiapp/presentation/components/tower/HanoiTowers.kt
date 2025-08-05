package com.sosorio.hanoiapp.presentation.components.tower

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
    moveDisk: (from: Int, to: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val numberOfRods = board.numberOfRods
    val numberOfTotalDisks = board.numberOfDisks

    val localDensity = LocalDensity.current
    var totalHeight by remember { mutableStateOf(0.dp) }
    var totalWidth by remember { mutableStateOf(0.dp) }

    var selectedFrom by remember { mutableStateOf<Int?>(null) }

    val diskHeight =
        remember(totalHeight, numberOfTotalDisks) {
            val minDiskHeight = totalHeight / numberOfTotalDisks
            val maxDiskHeight = 20.dp
            minOf(minDiskHeight, maxDiskHeight)
        }

    val towerWidth =
        remember(totalWidth, numberOfRods) {
            totalWidth / numberOfRods
        }

    Box(
        modifier =
            modifier.onGloballyPositioned { coordinates ->
                totalHeight = with(localDensity) { coordinates.size.height.toDp() }
                totalWidth = with(localDensity) { coordinates.size.width.toDp() }
            },
        contentAlignment = Alignment.BottomStart,
    ) {
        board.forEachIndexed { rodIndex, rod ->
            val isSelected = selectedFrom == rodIndex

            Box(
                modifier =
                    Modifier
                        .offset { IntOffset((towerWidth * rodIndex).roundToPx(), 0) }
                        .width(towerWidth)
                        .fillMaxHeight()
                        .clickable {
                            if (selectedFrom == null) {
                                selectedFrom = rodIndex
                            } else {
                                val from = selectedFrom!!
                                val to = rodIndex
                                moveDisk(from, to)
                                selectedFrom = null
                            }
                        },
            )

            rod.forEachIndexed { heightIndex, disk ->
                key(disk.index) {
                    val diskWidthFraction = (disk.index).toFloat() / numberOfTotalDisks
                    val diskWidth = towerWidth * diskWidthFraction

                    val targetX = (towerWidth * rodIndex) + ((towerWidth - diskWidth) / 2)
                    val targetY = -diskHeight * (rod.size - heightIndex)

                    val animatedOffset by animateIntOffsetAsState(
                        targetValue =
                            IntOffset(
                                x = with(localDensity) { targetX.toPx() }.toInt(),
                                y = with(localDensity) { targetY.toPx() }.toInt(),
                            ),
                        label = "diskOffset",
                    )

                    HanoiDisk(
                        disk = disk,
                        modifier =
                            Modifier
                                .width(diskWidth)
                                .height(diskHeight)
                                .offset { animatedOffset },
                    )
                }
            }

            if (isSelected) {
                Box(
                    modifier =
                        Modifier
                            .offset { IntOffset((towerWidth * rodIndex).roundToPx(), 0) }
                            .width(towerWidth)
                            .fillMaxHeight()
                            .border(
                                width = 3.dp,
                                color = Color.Red,
                                shape = RectangleShape,
                            ),
                )
            }
        }
    }
}
