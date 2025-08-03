package com.sosorio.hanoiapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun ControlButton(
    type: ControlButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor =
        when (type) {
            ControlButtonType.PAUSE -> MaterialTheme.colorScheme.primary
            ControlButtonType.PLAY -> MaterialTheme.colorScheme.primary
            ControlButtonType.RESTART -> MaterialTheme.colorScheme.tertiary
            ControlButtonType.NEXT -> MaterialTheme.colorScheme.tertiary
        }
    val contentColor =
        when (type) {
            ControlButtonType.PAUSE -> MaterialTheme.colorScheme.background
            ControlButtonType.PLAY -> MaterialTheme.colorScheme.background
            ControlButtonType.RESTART -> MaterialTheme.colorScheme.primary
            ControlButtonType.NEXT -> MaterialTheme.colorScheme.primary
        }
    val borderColor =
        when (type) {
            ControlButtonType.PAUSE -> Color.Transparent
            ControlButtonType.PLAY -> Color.Transparent
            ControlButtonType.RESTART -> MaterialTheme.colorScheme.primary
            ControlButtonType.NEXT -> MaterialTheme.colorScheme.primary
        }
    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier =
            modifier
                .clip(shape)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = shape,
                ).background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = type.icon,
                contentDescription = stringResource(type.contentDescriptionRes),
                tint = contentColor,
            )
        }
    }
}

enum class ControlButtonType(
    val icon: ImageVector,
    val contentDescriptionRes: Int,
) {
    PAUSE(
        icon = Icons.Default.Person,
        contentDescriptionRes = R.string.pause,
    ),
    PLAY(
        icon = Icons.Default.PlayArrow,
        contentDescriptionRes = R.string.play,
    ),
    RESTART(
        icon = Icons.Default.Refresh,
        contentDescriptionRes = R.string.restart,
    ),
    NEXT(
        icon = Icons.Default.Refresh,
        contentDescriptionRes = R.string.next,
    ),
}

@Preview
@Composable
private fun ControlButtonPreview() {
    HanoiAppTheme {
        ControlButton(
            type = ControlButtonType.PLAY,
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
