package com.sosorio.hanoiapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.domain.entities.HanoiGame
import com.sosorio.hanoiapp.presentation.components.appBar.HanoiAppBar
import com.sosorio.hanoiapp.presentation.components.button.ControlButtons
import com.sosorio.hanoiapp.presentation.components.label.MovementHistoryList
import com.sosorio.hanoiapp.presentation.components.sheet.ConfigurationSheet
import com.sosorio.hanoiapp.presentation.components.tower.HanoiTowers
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    handleIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showConfigurationSheet by remember { mutableStateOf(false) }

    if (showConfigurationSheet) {
        ModalBottomSheet(
            onDismissRequest = { showConfigurationSheet = false },
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            containerColor = MaterialTheme.colorScheme.tertiary,
        ) {
            ConfigurationSheet(
                configuration = uiState.configuration,
                onSaveClick = {
                    handleIntent(HomeIntent.ConfigureAlgorithm(it))
                    showConfigurationSheet = false
                },
                onCancelClick = { showConfigurationSheet = false },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }

    Column(
        modifier =
            modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HanoiAppBar(
            title = stringResource(R.string.app_name),
            onConfigurationClick = { showConfigurationSheet = true },
            modifier = Modifier.fillMaxWidth(),
        )
        HanoiTowers(
            board = uiState.towers,
            moveDisk = { from, to -> handleIntent(HomeIntent.MoveDisk(from, to)) },
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .weight(0.8F),
        )
        MovementHistoryList(
            movementHistory = uiState.movementHistory,
            modifier =
                Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth()
                    .weight(0.2F),
        )
        ControlButtons(
            onPlayClick = { handleIntent(HomeIntent.StartAlgorithm) },
            onRefreshClick = { handleIntent(HomeIntent.RestartAlgorithm) },
            isLoading = uiState.isLoading,
            modifier =
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val game = HanoiGame(5)
    HanoiAppTheme {
        HomeScreen(
            uiState =
                HomeUiState(
                    towers = game.towers,
                ),
            handleIntent = { },
        )
    }
}
