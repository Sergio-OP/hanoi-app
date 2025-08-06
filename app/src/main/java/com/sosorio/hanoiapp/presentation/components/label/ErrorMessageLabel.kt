package com.sosorio.hanoiapp.presentation.components.label

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun ErrorMessageLabel(
    message: String?,
    modifier: Modifier = Modifier,
) {
    Text(
        text = message ?: "",
        style =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
            ),
        modifier =
            modifier.padding(8.dp),
    )
}

@Preview
@Composable
private fun ErrorMessageLabelPreview() {
    HanoiAppTheme {
        ErrorMessageLabel(
            message = stringResource(R.string.something_went_wrong),
        )
    }
}
