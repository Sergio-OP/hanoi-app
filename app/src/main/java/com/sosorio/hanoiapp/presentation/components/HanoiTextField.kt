package com.sosorio.hanoiapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun HanoiTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Number,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = label,
            style =
                TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                ),
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle =
                TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                ),
            placeholder = { Text(text = label) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
        )
    }
}

@Preview
@Composable
private fun HanoiTextFieldPreview() {
    HanoiAppTheme {
        HanoiTextField(
            value = "",
            label = stringResource(R.string.number_of_disks),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun HanoiTextFieldFillPreview() {
    HanoiAppTheme {
        HanoiTextField(
            value = "12",
            label = stringResource(R.string.number_of_disks),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
