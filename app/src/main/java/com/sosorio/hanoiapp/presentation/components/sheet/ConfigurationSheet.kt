package com.sosorio.hanoiapp.presentation.components.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.presentation.components.button.HanoiButton
import com.sosorio.hanoiapp.presentation.components.button.HanoiButtonType
import com.sosorio.hanoiapp.presentation.components.textField.HanoiTextField
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun ConfigurationSheet(
    configuration: AlgorithmConfiguration,
    onSaveClick: (AlgorithmConfiguration) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var numberOfDisks by remember { mutableIntStateOf(configuration.numberOfDisks) }
    var timeInMs by remember { mutableLongStateOf(configuration.movementTimeInMs) }

    Column(
        modifier =
            modifier
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.configure_algorithm),
            style =
                TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                ),
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            HanoiTextField(
                value = numberOfDisks.toString(),
                label = stringResource(R.string.number_of_disks),
                onValueChange = { numberOfDisks = it.toIntOrNull() ?: 0 },
                modifier = Modifier.weight(1F),
            )
            HanoiTextField(
                value = timeInMs.toString(),
                label = stringResource(R.string.move_animation_time_ms),
                onValueChange = { timeInMs = it.toLongOrNull() ?: 0L },
                modifier = Modifier.weight(1F),
            )
        }
        Spacer(Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            HanoiButton(
                description = stringResource(R.string.cancel),
                onClick = onCancelClick,
                type = HanoiButtonType.SECONDARY,
                modifier = Modifier.weight(1F),
            )
            HanoiButton(
                description = stringResource(R.string.save),
                onClick = { onSaveClick(AlgorithmConfiguration(numberOfDisks, timeInMs)) },
                type = HanoiButtonType.MAIN,
                modifier = Modifier.weight(1F),
            )
        }
    }
}

@Preview
@Composable
private fun ConfigurationSheetPreview() {
    HanoiAppTheme {
        ConfigurationSheet(
            configuration =
                AlgorithmConfiguration(
                    numberOfDisks = 5,
                    movementTimeInMs = 1_000L,
                ),
            onSaveClick = {},
            onCancelClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
