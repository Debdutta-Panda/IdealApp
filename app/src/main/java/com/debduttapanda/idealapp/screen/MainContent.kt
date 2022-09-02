package com.debduttapanda.idealapp.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.debduttapanda.idealapp.Routes
import com.debduttapanda.idealapp.myComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.splash
    ){
        myComposable(
            Routes.splash
        ){
            SplashScreen(navController)
        }

        myComposable(
            Routes.home,
        ){
            HomeScreen(navController)
        }
        myComposable(
            Routes.add
        ) {
            AddScreen(navController)
        }
    }
}