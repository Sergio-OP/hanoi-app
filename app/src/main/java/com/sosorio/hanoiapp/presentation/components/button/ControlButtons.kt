package com.sosorio.hanoiapp.presentation.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun ControlButtons(
    onPlayClick: () -> Unit,
    onRefreshClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    var centralButtonType by remember { mutableStateOf(ControlButtonType.PLAY) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        ControlButton(
            type = ControlButtonType.RESTART,
            onClick = onRefreshClick,
            modifier = Modifier.weight(1F),
        )
        ControlButton(
            type = centralButtonType,
            onClick = onPlayClick,
            modifier = Modifier.weight(1F),
            isLoading = isLoading,
        )
    }
}

@Preview
@Composable
private fun ControlButtonsPreview() {
    HanoiAppTheme {
        ControlButtons(
            modifier = Modifier.fillMaxWidth(),
            onPlayClick = { },
            onRefreshClick = { },
            isLoading = false,
        )
    }
}
