package com.debduttapanda.idealapp.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.debduttapanda.idealapp.ui.theme.IdealAppTheme
import com.debduttapanda.idealapp.viewmodels.MockHomeViewModel
import com.debduttapanda.idealapp.viewmodels.MockHomeViewModelWithTasks
import org.junit.Rule
import org.junit.Test

class MainContentKtTest{
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Test
    fun noTaskTest() {
        // Start the app
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            IdealAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(navController = navController, vm = MockHomeViewModel())
                }
            }
        }

        composeTestRule.onNode(hasContentDescription("no_items_yet")).assertIsDisplayed()
    }
    @Test
    fun oneTaskTest() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            IdealAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(navController = navController, vm = MockHomeViewModelWithTasks())
                }
            }
        }

        composeTestRule.onNode(hasContentDescription("no_items_yet")).assertDoesNotExist()
        composeTestRule.onNode(hasContentDescription("task_item1")).assertIsDisplayed()
    }
}
