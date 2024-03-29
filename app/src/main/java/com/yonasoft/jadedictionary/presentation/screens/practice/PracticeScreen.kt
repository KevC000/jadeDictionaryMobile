package com.yonasoft.jadedictionary.presentation.screens.practice

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yonasoft.jadedictionary.presentation.screens.practice.practice_mode_selection.PracticeModeSettings
import com.yonasoft.jadedictionary.presentation.screens.practice.practice_sessions.PracticeResultsScreen
import com.yonasoft.jadedictionary.presentation.screens.practice.practice_sessions.PracticeSessionContainer
import com.yonasoft.jadedictionary.presentation.screens.practice.practice_word_select.PracticeWordSelect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeScreen(
    navController: NavController,
    sharedViewModel: PracticeSharedViewModel = hiltViewModel()
) {
    val screen = sharedViewModel.screen

    when (screen.intValue) {
        0 -> PracticeModeSettings(sharedViewModel = sharedViewModel) {
            screen.intValue = 1
        }

        1 -> PracticeWordSelect(sharedViewModel = sharedViewModel) {
            screen.intValue = 2
        }

        2 -> PracticeSessionContainer(sharedViewModel = sharedViewModel)

        3 -> PracticeResultsScreen(navController = navController, sharedViewModel = sharedViewModel)
    }
}