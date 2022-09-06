package com.debduttapanda.idealapp

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.debduttapanda.idealapp.screen.HomeScreen
import com.debduttapanda.idealapp.ui.theme.IdealAppTheme
import com.debduttapanda.idealapp.viewmodels.HomeViewModelImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTestWithHilt {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()
    }

    @Test
    fun add_button_exists_and_has_task_items() {
        composeTestRule.activity.setContent {
            IdealAppTheme() {
                val navController = rememberNavController()
                HomeScreen(
                    navController = navController,
                    vm = composeTestRule.activity.viewModels<HomeViewModelImpl>().value)
            }
        }
        composeTestRule.waitUntil(5000){
            composeTestRule.onAllNodes(hasContentDescriptionPattern("task_item.*".toRegex(RegexOption.IGNORE_CASE)))
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNode(
            hasContentDescription("add_button"),
            useUnmergedTree = true
        ).assertExists()
    }

    @Test
    fun given_compose_view_has_desired_header_text() {
        composeTestRule.activity.setContent {
            IdealAppTheme() {
                val navController = rememberNavController()
                HomeScreen(
                    navController = navController,
                    vm = composeTestRule.activity.viewModels<HomeViewModelImpl>().value)
            }
        }
        composeTestRule.onNode(
            hasContentDescription("add_button"),
            useUnmergedTree = true
        ).assertExists()
    }
}

fun hasContentDescriptionPattern(
    pattern: Regex,
    ignoreCase: Boolean = false
): SemanticsMatcher {
    return SemanticsMatcher(
        "${SemanticsProperties.ContentDescription.name} = '$pattern' (ignoreCase: $ignoreCase)"
    ) {
        it.config.getOrNull(SemanticsProperties.ContentDescription)
            ?.any { item -> item.matches(pattern) } ?: false
    }
}