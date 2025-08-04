package com.sosorio.hanoiapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sosorio.hanoiapp.R
import com.sosorio.hanoiapp.domain.entities.HanoiGame
import com.sosorio.hanoiapp.presentation.components.button.ControlButtons
import com.sosorio.hanoiapp.presentation.components.appBar.HanoiAppBar
import com.sosorio.hanoiapp.presentation.components.tower.HanoiTowers
import com.sosorio.hanoiapp.ui.theme.HanoiAppTheme

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    handleIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
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
            onConfigurationClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
        HanoiTowers(
            board = uiState.towers,
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .weight(1F),
        )
        ControlButtons(
            onPlayClick = { handleIntent(HomeIntent.StartGame) },
            onRefreshClick = { handleIntent(HomeIntent.RefreshGame) },
            onNextClick = { TODO() },
            onPauseClick = { handleIntent(HomeIntent.StartGame) },
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
