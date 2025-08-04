package com.sosorio.hanoiapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun HanoiButton(
    description: String,
    onClick: () -> Unit,
    type: HanoiButtonType,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors =
            ButtonDefaults.buttonColors(
                containerColor =
                    when (type) {
                        HanoiButtonType.MAIN -> MaterialTheme.colorScheme.primary
                        HanoiButtonType.SECONDARY -> MaterialTheme.colorScheme.background
                    },
                contentColor =
                    when (type) {
                        HanoiButtonType.MAIN -> MaterialTheme.colorScheme.background
                        HanoiButtonType.SECONDARY -> MaterialTheme.colorScheme.primary
                    },
            ),
        border =
            BorderStroke(
                width = 1.dp,
                color =
                    when (type) {
                        HanoiButtonType.MAIN -> Color.Transparent
                        HanoiButtonType.SECONDARY -> MaterialTheme.colorScheme.primary
                    },
            ),
    ) {
        Text(
            text = description,
            style =
                TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                ),
        )
    }
}

enum class HanoiButtonType { MAIN, SECONDARY }

@Preview
@Composable
private fun HanoiButtonPreview() {
    HanoiAppTheme {
        HanoiButton(
            description = stringResource(R.string.save),
            onClick = {},
            type = HanoiButtonType.MAIN,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun HanoiSecondaryButtonPreview() {
    HanoiAppTheme {
        HanoiButton(
            description = stringResource(R.string.cancel),
            onClick = {},
            type = HanoiButtonType.SECONDARY,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
