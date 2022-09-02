package com.debduttapanda.idealapp

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

data class Toaster(
    private val context: Context
){
    fun toast(message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message,duration).show()
    }

    fun stringResource(@StringRes id: Int): String{
        return context.getString(id)
    }
}

typealias UIScope = suspend (NavHostController, LifecycleOwner, Toaster?)->Unit

fun MutableState<UIScope?>.scope(block: UIScope?){
    this.value = {navHostController, lifecycleOwner,toaster ->
        block?.invoke(navHostController,lifecycleOwner,toaster)
        this.value = null
    }
}

suspend fun MutableState<UIScope?>.forward(
    navHostController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    toaster: Toaster? = null
){
    this.value?.invoke(navHostController,lifecycleOwner,toaster)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.myComposable(
    route: String,
    content: @Composable (AnimatedVisibilityScope) -> Unit
){
    composable(
        route,
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
        content(this)
    }
}

data class Value<T>(
    val initialValue: T,
    val interceptor: (T)->T = {
        it
    },
    val onChange: (T)->Unit = {}
){
    private val _data = mutableStateOf(initialValue)
    val live: State<T> = _data
    fun change(newValue: T){
        _data.value = interceptor(newValue)
        onChange(_data.value)
    }
    var value : T
    set(newValue){
        change(newValue)
    }
    get() = live.value
}