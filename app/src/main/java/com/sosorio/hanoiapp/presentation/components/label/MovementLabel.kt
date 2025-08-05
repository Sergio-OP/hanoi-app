package com.sosorio.hanoiapp.presentation.components.label

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun MovementLabel(
    movementLog: MovementLog,
    modifier: Modifier = Modifier,
) {
    Text(
        text =
            stringResource(
                id = R.string.step_movement_description,
                movementLog.diskIndex,
                movementLog.fromRod,
                movementLog.toRod,
            ),
        style =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
            ),
        modifier =
            modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(4.dp),
    )
}

data class MovementLog(
    val diskIndex: Int,
    val fromRod: String,
    val toRod: String,
)

@Preview
@Composable
private fun MovementLabelPreview() {
    HanoiAppTheme {
        MovementLabel(
            movementLog =
                MovementLog(
                    diskIndex = 1,
                    fromRod = "A",
                    toRod = "B",
                ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
