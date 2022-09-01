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
        composable(
            Routes.splash,
            enterTransition = null/*{
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }*/,
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            }
        ){
            SplashScreen(navController)
        }

        composable(
            Routes.home,
            enterTransition = null/*{
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }*/,
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            popEnterTransition = null/*{
            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
        }*/,
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            }
        ){
            HomeScreen(navController)
        }
        myComposable(
            Routes.add
        ) {
            AddScreen()
        }
    }
}