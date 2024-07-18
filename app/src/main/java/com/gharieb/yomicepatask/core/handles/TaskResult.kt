package com.gharieb.yomicepatask.core.handles

import com.gharieb.yomicepatask.core.exceptions.YomicepaExceptions

sealed class TaskResult<out T>{
    data class Success<out R>(val value: R): TaskResult<R>()

    data class Error(
        val exception: YomicepaExceptions
    ): TaskResult<Nothing>()

    data object Loading: TaskResult<Nothing>()
}