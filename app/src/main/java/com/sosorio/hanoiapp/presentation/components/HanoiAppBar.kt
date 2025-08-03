package com.sosorio.hanoiapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HanoiAppBar(
    title: String,
    onConfigurationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.background,
            ),
        actions = {
            IconButton(
                onClick = onConfigurationClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(R.string.configuration),
                    tint = MaterialTheme.colorScheme.background,
                )
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun HanoiAppBarPreview() {
    HanoiAppTheme {
        HanoiAppBar(
            title = stringResource(R.string.app_name),
            onConfigurationClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
