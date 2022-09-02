package com.debduttapanda.idealapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class Dialoger{
    private val _enabled = mutableStateOf(false)
    val enabled: State<Boolean> = _enabled
    private var onDoneCallback: (Any?)->Unit = {}
    private var onDoneMediator: ((Any?)->Boolean)? = { true }
    suspend fun show(
        onDoneMediator: ((Any?)->Boolean)? = { true }
    ) = suspendCancellableCoroutine<Any?> { coroutine->
        this.onDoneMediator = onDoneMediator
        _enabled.value = true
        onDoneCallback = {param->
            coroutine.resume(param)
        }
    }

    fun onDone(param: Any?) {
        if(onDoneMediator?.invoke(param)==true){
            _enabled.value = false
            onDoneCallback.invoke(param)
        }
    }
}