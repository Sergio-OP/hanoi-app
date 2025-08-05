package com.sosorio.hanoiapp.presentation.components.label

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun MovementHistoryList(
    movementHistory: List<MovementLog>,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val shape = RoundedCornerShape(4.dp)

    LaunchedEffect(movementHistory.size) {
        if (movementHistory.isNotEmpty()) {
            listState.animateScrollToItem(movementHistory.lastIndex)
        }
    }

    LazyColumn(
        state = listState,
        modifier =
            modifier
                .fillMaxWidth()
                .clip(shape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = shape,
                ),
    ) {
        items(movementHistory) { movementLog ->
            MovementLabel(
                movementLog = movementLog,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
