package com.sosorio.hanoiapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sosorio.hanoiapp.domain.entities.HanoiDisk
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun HanoiDisk(
    disk: HanoiDisk,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(100)

    Box(
        modifier =
            modifier
                .clip(shape = shape)
                .background(color = Color(disk.color))
                .border(width = 1.dp, color = Color.Black, shape = shape)
                .fillMaxWidth(),
    )
}

@Preview
@Composable
private fun HanoiDiskPreview() {
    HanoiAppTheme {
        HanoiDisk(
            disk =
                HanoiDisk(
                    index = 1,
                    color = 0xFFFF0000,
                ),
            modifier =
                Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
        )
    }
}
